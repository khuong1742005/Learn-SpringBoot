package com.example.testDemo.services.impl;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.dtos.requests.IntrospectRequest;
import com.example.testDemo.dtos.response.AuthResponse;
import com.example.testDemo.dtos.response.IntrospectResponse;
import com.example.testDemo.repositories.UserRepository;
import com.example.testDemo.services.interfaces.IAuthService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IAuthServiceImpl implements IAuthService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.secret}")
    protected String SIGNER_KEY;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found!"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) throw new RuntimeException("Authentication failed!");
        var token = generateToken(request.getUsername());
        return AuthResponse.builder().token(token).authenticated(true).build();
    }

    // Verify JWT token
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(jwsVerifier);

        return IntrospectResponse.builder()
                .valid((verified && expirationTime.after(new Date())))
                .build();
    }

    private String generateToken(String username) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().
                subject(username).
                issuer("demo.com").
                issueTime(new Date()).
                expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())).
                claim("Custom claim", "Custom").
                build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}

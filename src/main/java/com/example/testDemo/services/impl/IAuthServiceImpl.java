package com.example.testDemo.services.impl;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.dtos.response.AuthResponse;
import com.example.testDemo.repositories.UserRepository;
import com.example.testDemo.services.interfaces.IAuthService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class IAuthServiceImpl implements IAuthService {
    @Autowired
    UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY = "IOg/flCDdPNoC2+j4/G0m3l8FhX7paEreFwAeO2eFf4YhZabokoyI38afEtUGut7\n";

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found!"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) throw new RuntimeException("Authentication failed!");
        var token = generateToken(request.getUsername());
        return AuthResponse.builder().token(token).authenticated(true).build();
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

package com.example.testDemo.repositories;

import com.example.testDemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsUserByPhoneNumber(String phoneNumber);
}

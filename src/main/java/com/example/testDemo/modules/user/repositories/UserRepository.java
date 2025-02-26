package com.example.testDemo.modules.user.repositories;

import com.example.testDemo.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByStt(int stt);
}

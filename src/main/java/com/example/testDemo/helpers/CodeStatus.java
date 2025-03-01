package com.example.testDemo.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public enum CodeStatus {
    USER_CREATED_SUCCESS(201, "Created successfully!"),
    USER_CREATED_FAILED(400, "Created failed!"),
    USER_LOGIN_SUCCESS(200, "Logged in successfully!"),
    USER_LOGIN_FAILED(401, "Logged in failed!"),
    USER_GET_SUCCESS(200, "OK!"),
    USER_GET_FAILED(404, "User not found!"),
    USER_UPDATED_SUCCESS(200, "Updated user successfully!"),
    USER_UPDATED_FAILED(400, "Updated user failed!"),
    USER_DELETE_SUCCESS(200, "Deleted user successfully!"),
    USER_DELETE_FAILED(400, "Deleted user failed!"),
    USER_NOT_MATCH(401, "Unauthorized!"),
    USER_NOT_LOGGED_IN(403, "User not logged in"),
    USER_EXISTED(400, "user existed")
    ;
    private int code;
    private String message;
}

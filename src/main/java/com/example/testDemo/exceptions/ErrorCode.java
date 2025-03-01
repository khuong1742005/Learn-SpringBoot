package com.example.testDemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(400, "user existed")
    ;
    private int code;
    private String message;
}

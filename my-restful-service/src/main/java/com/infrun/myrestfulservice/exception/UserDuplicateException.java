package com.infrun.myrestfulservice.exception;

public class UserDuplicateException extends Exception {
    public UserDuplicateException(String message) {
        super(message);
    }
}

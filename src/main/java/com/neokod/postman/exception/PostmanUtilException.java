package com.neokod.postman.exception;

public class PostmanUtilException extends RuntimeException {

    private final String code;

    public PostmanUtilException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() { return code; }
}

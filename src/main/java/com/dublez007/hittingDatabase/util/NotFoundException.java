package com.dublez007.hittingDatabase.util;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
    }
}

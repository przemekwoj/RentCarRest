package com.przemo.rentcar.exceptions.particularErrors;

public class NotFoundEntity extends RuntimeException {
    public NotFoundEntity() {
    }

    public NotFoundEntity(String message) {
        super(message);
    }

    public NotFoundEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundEntity(Throwable cause) {
        super(cause);
    }
}

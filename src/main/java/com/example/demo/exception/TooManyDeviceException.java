package com.example.demo.exception;

/**
 * @author tangxiangan
 */
public class TooManyDeviceException extends RuntimeException {
    public TooManyDeviceException() {
        super();
    }

    public TooManyDeviceException(String message) {
        super(message);
    }

    public TooManyDeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyDeviceException(Throwable cause) {
        super(cause);
    }

}

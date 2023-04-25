package com.kdan.compdfkit.exception;

/**
 * @author tangxiangan
 */
public class BackendRuntimeException extends RuntimeException {
    public BackendRuntimeException() {
        super();
    }

    public BackendRuntimeException(String message) {
        super(message);
    }

    public BackendRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BackendRuntimeException(Throwable cause) {
        super(cause);
    }

}

package io.github.tangxiangan.exception;

/**
 * @author tangxiangan
 */
public class BackendRuntimeException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BackendRuntimeException() {
        super();
    }

    public BackendRuntimeException(String message) {
        super(message);
    }

    public BackendRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BackendRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BackendRuntimeException(Throwable cause) {
        super(cause);
    }

}

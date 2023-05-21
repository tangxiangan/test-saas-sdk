package cn.kdan.compdfkit.exception;

/**
 * @author tangxiangan
 */
public class BackendRuntimeException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BackendRuntimeException() {
        super();
    }

    public BackendRuntimeException(String message) {
        super(message);
    }

    public BackendRuntimeException(Integer code, String message) {
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

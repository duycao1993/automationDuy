package utilities.ExceptionUtil;

public class SizeViolationException extends LoginFailedException {
    public SizeViolationException() {
        super();
    }

    public SizeViolationException(String message) {
        super(message);
    }

    public SizeViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SizeViolationException(Throwable cause) {
        super(cause);
    }

    protected SizeViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getExceptionDetail() {
        return "The lenght is incorrect";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

package utilities.ExceptionUtil;

public class PasswordIncorrectException extends LoginFailedException {
    public PasswordIncorrectException() {
        super();
    }

    public PasswordIncorrectException(String message) {
        super(message);
    }

    public PasswordIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordIncorrectException(Throwable cause) {
        super(cause);
    }

    protected PasswordIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getExceptionDetail() {
        return "Password is incorrect";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

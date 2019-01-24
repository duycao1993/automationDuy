package utilities.ExceptionUtil;

public class LoginFailedException extends Exception{
    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getExceptionDetail(){
        return "Login failed";
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}

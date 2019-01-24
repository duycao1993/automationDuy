package utilities.ExceptionUtil;

public class UnexpectedErrorException extends LoginFailedException{
    public UnexpectedErrorException() {
        super();
    }

    public UnexpectedErrorException(String message) {
        super(message);
    }

    public UnexpectedErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedErrorException(Throwable cause) {
        super(cause);
    }

    protected UnexpectedErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getExceptionDetail(){
        return "Unexpected error";
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}

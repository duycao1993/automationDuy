package utilities.ExceptionUtil;

public class UnexpectedErrorException extends Exception{
    public UnexpectedErrorException() {
        super();
    }

    public UnexpectedErrorException(String message) {
        super(message);
    }

    public String getExceptionDetail(){
        return "Unexpected error";
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}

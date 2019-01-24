package utilities.ExceptionUtil;

public class FormatViolationException extends Exception {
    public FormatViolationException() {
        super();
    }

    public FormatViolationException(String message) {
        super(message);
    }

    public String getExceptionDetail() {
        return "The lenght is incorrect";
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}

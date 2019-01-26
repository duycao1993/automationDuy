package Utilities.ExceptionUtil;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException() {
        super();
    }

    public PasswordIncorrectException(String message) {
        super(message);
    }

    public String getExceptionDetail() {
        return "Password is incorrect";
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}

package exceptions;

public class InvalidFigureFormatException extends Exception {
    public InvalidFigureFormatException(String message) {
        super(message);
    }
    public InvalidFigureFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
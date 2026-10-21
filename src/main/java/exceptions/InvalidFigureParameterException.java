package exceptions;

public class InvalidFigureParameterException extends RuntimeException {
    public InvalidFigureParameterException(String message) {
        super(message);
    }
}

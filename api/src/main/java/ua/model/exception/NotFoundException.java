package ua.model.exception;

/**
 * @author Andrii Severin
 */
public class NotFoundException extends ServerException {
    public NotFoundException() {
        super("Not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}

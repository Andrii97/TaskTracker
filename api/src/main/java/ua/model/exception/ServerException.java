package ua.model.exception;

/**
 * @author Andrii Severin
 */
public class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }
}

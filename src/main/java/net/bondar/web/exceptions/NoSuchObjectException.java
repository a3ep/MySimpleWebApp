package net.bondar.web.exceptions;

/**
 * Created by AzeraL on 28.01.2016.
 */
public class NoSuchObjectException extends WebAppException {
    public NoSuchObjectException() {
    }

    public NoSuchObjectException(String message) {
        super(message);
    }
}

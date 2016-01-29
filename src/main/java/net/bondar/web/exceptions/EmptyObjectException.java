package net.bondar.web.exceptions;

/**
 * Created by AzeraL on 28.01.2016.
 */
public class EmptyObjectException extends WebAppException {
    public EmptyObjectException() {
    }

    public EmptyObjectException(String message) {
        super(message);
    }
}

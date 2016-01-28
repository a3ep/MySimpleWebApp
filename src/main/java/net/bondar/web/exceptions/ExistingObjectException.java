package net.bondar.web.exceptions;

/**
 * Created by AzeraL on 28.01.2016.
 */
public class ExistingObjectException extends WebAppException{
    public ExistingObjectException() {
    }

    public ExistingObjectException(String message) {
        super(message);
    }
}

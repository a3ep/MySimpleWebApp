package net.bondar.web.exceptions;

/**
 * Created by AzeraL on 13.10.2015.
 */
public class ContactsNotFoundException extends WebAppException {
    public ContactsNotFoundException() {
    }

    public ContactsNotFoundException(String message) {
        super(message);
    }
}

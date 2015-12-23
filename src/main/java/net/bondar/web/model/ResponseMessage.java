package net.bondar.web.model;

/**
 * Created by AzeraL on 30.11.2015.
 */
public class ResponseMessage {
    private String status;
    private Contact contact;
    private ContactFieldsTransport cft;
    private String errorMessage;

    public static ResponseMessage okMessage(Contact contact) {
        return new ResponseMessage("OK", contact);
    }

    public static ResponseMessage okMessage(ContactFieldsTransport cft){
        return new ResponseMessage("OK", cft);
    }

    public static ResponseMessage errorMessage(String message) {
        return new ResponseMessage("ERROR", message);
    }

    private ResponseMessage(String status, Contact contact) {
        this.status = status;
        this.contact = contact;
    }

    private ResponseMessage(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    private ResponseMessage(String status, ContactFieldsTransport cft){
        this.status = status;
        this.cft = cft;
    }

    public String getStatus() {
        return status;
    }

    public Contact getContact() {
        return contact;
    }

    public ContactFieldsTransport getCft() {
        return cft;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

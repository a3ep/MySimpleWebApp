package net.bondar.web.model;

import net.bondar.web.model.dto.ContactDto;

/**
 * Created by AzeraL on 30.11.2015.
 */
public class ResponseMessage {
    private String status;
    private Contact contact;
    private ContactDto contactDto;
    private String errorMessage;

    public static ResponseMessage okMessage(Contact contact) {
        return new ResponseMessage("OK", contact);
    }

    public static ResponseMessage okMessage(ContactDto contactDto){
        return new ResponseMessage("OK", contactDto);
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

    private ResponseMessage(String status, ContactDto contactDto){
        this.status = status;
        this.contactDto = contactDto;
    }

    public String getStatus() {
        return status;
    }

    public Contact getContact() {
        return contact;
    }

    public ContactDto getContactDto() {
        return contactDto;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

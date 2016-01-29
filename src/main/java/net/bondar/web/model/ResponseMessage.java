package net.bondar.web.model;

import net.bondar.web.model.dto.ContactDto;

/**
 * Created by AzeraL on 30.11.2015.
 */
public class ResponseMessage {
    private String status;
    private ContactDto contactDto;
    private Hobby hobby;
    private Place place;
    private String message;
    private String renderedHtml;

    public static ResponseMessage okMessage(ContactDto contactDto) {
        return new ResponseMessage("OK", contactDto);
    }

    public static ResponseMessage okMessage(ContactDto contactDto, String renderedHtml) {
        return new ResponseMessage("OK", contactDto, renderedHtml);
    }

    public static ResponseMessage okMessage(Hobby hobby) {
        return new ResponseMessage("OK", hobby);
    }

    public static ResponseMessage okMessage(Place place) {
        return new ResponseMessage("OK", place);
    }

    public static ResponseMessage okMessage(String message) {
        return new ResponseMessage("OK", message);
    }

    public static ResponseMessage errorMessage(String message) {
        return new ResponseMessage("ERROR", message);
    }

    private ResponseMessage(String status, String errorMessage) {
        this.status = status;
        this.message = errorMessage;
    }

    private ResponseMessage(String status, ContactDto contactDto) {
        this.status = status;
        this.contactDto = contactDto;
    }

    private ResponseMessage(String status, ContactDto contactDto, String renderedHtml) {
        this.status = status;
        this.contactDto = contactDto;
        this.renderedHtml = renderedHtml;
    }

    private ResponseMessage(String status, Hobby hobby) {
        this.status = status;
        this.hobby = hobby;
    }

    private ResponseMessage(String status, Place place) {
        this.status = status;
        this.place = place;
    }

    public String getStatus() {
        return status;
    }

    public ContactDto getContactDto() {
        return contactDto;
    }

    public String getMessage() {
        return message;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public Place getPlace() {
        return place;
    }

    public String getRenderedHtml() {
        return renderedHtml;
    }
}

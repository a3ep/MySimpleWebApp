package net.bondar.web.model;

import net.bondar.web.model.dto.ContactDto;

import java.util.List;

/**
 * Created by AzeraL on 30.11.2015.
 */
public class ResponseMessage {
    private String status;
    private Contact contact;
    private ContactDto contactDto;
    private Hobby hobby;
    private Place place;
    private String message;
    private String renderedHtml;
    private List<Message> messages;

    public static ResponseMessage okMessage(Contact contact) {
        return new ResponseMessage("OK", contact);
    }

    public static ResponseMessage okMessage(ContactDto contactDto){
        return new ResponseMessage("OK", contactDto);
    }

    public static ResponseMessage okMessage(ContactDto contactDto, String renderedHtml){
        return new ResponseMessage("OK", contactDto, renderedHtml);
    }

    public static ResponseMessage okMessage(Hobby hobby){
        return new ResponseMessage("OK", hobby);
    }

    public static ResponseMessage okMessage(Place place){
        return new ResponseMessage("OK", place);
    }

    public static ResponseMessage okMessage(String message){
        return new ResponseMessage("OK", message);
    }

    public static ResponseMessage okMessage(ContactDto contactDto, List<Message> messages){
        return new ResponseMessage("OK", contactDto, messages);
    }

    public static ResponseMessage okMessage(List<Message> messages){
        return new ResponseMessage("OK", messages);
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
        this.message = errorMessage;
    }

    private ResponseMessage(String status, ContactDto contactDto){
        this.status = status;
        this.contactDto = contactDto;
    }

    private ResponseMessage(String status, ContactDto contactDto, String renderedHtml){
        this.status = status;
        this.contactDto = contactDto;
        this.renderedHtml = renderedHtml;
    }

    private ResponseMessage(String status, Hobby hobby){
        this.status = status;
        this.hobby = hobby;
    }

    private ResponseMessage(String status, Place place) {
        this.status = status;
        this.place = place;
    }

    private ResponseMessage(String status, ContactDto contactDto, List<Message> messages){
        this.status = status;
        this.contactDto = contactDto;
        this.messages = messages;
    }

    private ResponseMessage(String status, List<Message> messages){
        this.status = status;
        this.messages = messages;
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

    public String getMessage() {
        return message;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public Place getPlace() {
        return place;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getRenderedHtml() {
        return renderedHtml;
    }
}

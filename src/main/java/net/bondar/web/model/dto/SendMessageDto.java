package net.bondar.web.model.dto;

/**
 * Created by AzeraL on 25.01.2016.
 */
public class SendMessageDto {
    private String message;

    public SendMessageDto() {
    }

    public SendMessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

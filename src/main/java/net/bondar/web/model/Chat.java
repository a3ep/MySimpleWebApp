package net.bondar.web.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "CHAT")
public class Chat extends AbstractEntity{

    @OneToOne
    private Contact userTo;

    @ManyToMany
    private List<Message> chatMessages;


    public Chat(){
        super();
    }

    public Chat(long id){
        super(id);
    }

    public Chat(Contact userTo) {
        this.userTo = userTo;
        this.chatMessages = new ArrayList<>();
    }



    public Contact getUserTo() {
        return userTo;
    }

    public void setUserTo(Contact userTo) {
        this.userTo = userTo;
    }

    public List<Message> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + super.getId() +
                ", userTo=" + userTo +
                ", chatMessages=" + chatMessages +
                '}';
    }
}

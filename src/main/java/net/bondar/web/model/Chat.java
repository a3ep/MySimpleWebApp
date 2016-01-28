package net.bondar.web.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "CHAT")
public class Chat extends AbstractEntity{

    @OneToMany
    private List<Message> messages;


    public Chat(){
        super();
        this.messages = new ArrayList<>();
    }

    public Chat(long id){
        super(id);
        this.messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + super.getId() +
                ", messages=" + messages +
                '}';
    }
}

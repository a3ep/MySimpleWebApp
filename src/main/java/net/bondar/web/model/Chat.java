package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "CHAT")
public class Chat extends AbstractEntity{

    @OneToOne
    private Contact userTo;

    @OneToMany
    private Set<Message> chatMessages;


    public Chat(){
        super();
    }

    public Chat(long id){
        super(id);
    }

    public Chat(Contact userTo) {
        this.userTo = userTo;
        this.chatMessages = new HashSet<>();
    }



    public Contact getUserTo() {
        return userTo;
    }

    public void setUserTo(Contact userTo) {
        this.userTo = userTo;
    }

    public Set<Message> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(Set<Message> chatMessages) {
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

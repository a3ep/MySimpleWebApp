package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "CHAT")
public class Chat extends AbstractEntity {

    @OneToMany
    private List<Message> messages;

    public Chat() {
        super();
        this.messages = new ArrayList<>();
    }

    public Chat(long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        return !(messages != null ? !messages.equals(chat.messages) : chat.messages != null);

    }

    @Override
    public int hashCode() {
        return messages != null ? messages.hashCode() : 0;
    }
}

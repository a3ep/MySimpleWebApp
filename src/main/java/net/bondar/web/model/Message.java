package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "MESSAGE")
public class Message extends AbstractEntity{

    @OneToOne
    private Contact userFrom;

    @OneToOne
    private Contact userTo;

    private Date date;

    private String content;


    public Message(){
        super();
    }

    public Message(long id){
        super(id);
    }

    public Message(Contact from, Contact to, Date date, String content) {
        this.userFrom = from;
        this.userTo = to;
        this.date = date;
        this.content = content;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Contact getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Contact userFrom) {
        this.userFrom = userFrom;
    }

    public Contact getUserTo() {
        return userTo;
    }

    public void setUserTo(Contact userTo) {
        this.userTo = userTo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + super.getId() +
                ", date=" + date + '\'' +
                ", content='" + content + '\'' +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;
        if (Long.compare(message.getId(), super.getId()) !=0) return false;
        if (userFrom != null ? !userFrom.equals(message.userFrom) : message.userFrom != null) return false;
        if (userTo != null ? !userTo.equals(message.userTo) : message.userTo != null) return false;
        if (date != null ? !date.equals(message.date) : message.date != null) return false;
        return !(content != null ? !content.equals(message.content) : message.content != null);

    }

    @Override
    public int hashCode() {
        int result = userFrom != null ? userFrom.hashCode() : 0;
        result = 31 * result + (userTo != null ? userTo.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}

package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "MESSAGE")
public class Message extends AbstractEntity{

    @OneToOne
    private Contact from;

    private LocalDateTime date;

    private String content;


    public Message(){
        super();
    }

    public Message(long id){
        super(id);
    }

    public Message(Contact from, LocalDateTime date, String content) {
        this.from = from;
        this.date = date;
        this.content = content;
    }



    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Contact getFrom() {
        return from;
    }

    public void setFrom(Contact from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + super.getId() +
                ", date=" + date + '\'' +
                ", content='" + content + '\'' +
                ", from=" + from +
                '}';
    }
}

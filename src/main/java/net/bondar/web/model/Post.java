package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "POST")
public class Post extends AbstractEntity{

    private Contact contactFrom;

    private String content;

    private LocalDateTime date;


    public Post(){
        super();
    }

    public Post(long id){
        super(id);
    }

    public Post(Contact contactFrom, String content, LocalDateTime date) {
        this.contactFrom = contactFrom;
        this.content = content;
        this.date = date;
    }



    public Contact getContactFrom() {
        return contactFrom;
    }

    public void setContactFrom(Contact contactFrom) {
        this.contactFrom = contactFrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + super.getId() +
                ", contactFrom=" + contactFrom +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}

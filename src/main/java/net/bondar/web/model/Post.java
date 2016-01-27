package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "POST")
public class Post extends AbstractEntity{

    @OneToOne
    private Contact contactFrom;

    private String content;

    private Date date;
    @OneToMany
    private List<Post> responses;


    public Post(){
        super();
    }

    public Post(long id){
        super(id);
    }

    public Post(Contact contactFrom, String content, Date date) {
        this.contactFrom = contactFrom;
        this.content = content;
        this.date = date;
        this.responses = new ArrayList<>();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Post> getResponses() {
        return responses;
    }

    public void setResponses(List<Post> responses) {
        this.responses = responses;
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

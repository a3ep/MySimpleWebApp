package net.bondar.web.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "HOBBY")
public class Hobby extends AbstractEntity{

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;



    public Hobby(){
        super();
    }

    public Hobby(long id){
        super(id);
    }

    public Hobby(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hobby hobby = (Hobby) o;

        if (Long.compare(hobby.getId(), super.getId()) !=0) return false;
        if (title != null ? !title.equals(hobby.title) : hobby.title != null) return false;
        return !(description != null ? !description.equals(hobby.description) : hobby.description != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id='" + super.getId() + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

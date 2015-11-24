package net.bondar.web.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Azeral on 28.10.2015.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AbstractEntity(){}

    public AbstractEntity(long id){
        this.id=id;
    }
}

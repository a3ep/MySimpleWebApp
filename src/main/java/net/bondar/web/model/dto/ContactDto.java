package net.bondar.web.model.dto;

import java.util.Date;

/**
 * Created by Flash on 16.12.2015.
 */
public class ContactDto {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public ContactDto(){};
    public ContactDto(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

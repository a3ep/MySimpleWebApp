package net.bondar.web.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "CONTACT")
public class Contact extends AbstractEntity{

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String userName;

    private String password;

    private String confirmPassword;

    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Hobby> hobbies;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Place> places;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Contact> friendList;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Chat> conversation;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Post> posts;


    public Contact(){
        super();
    }

    public Contact(long id){
        super(id);
    }

    public Contact(String firstName, String lastName, Date birthDate, String userName, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.photo="../../resources/img/my.png";
        this.hobbies = new HashSet<>();
        this.places = new HashSet<>();
        this.friendList = new HashSet<>();
        this.conversation = new HashSet<>();
        this.posts = new HashSet<>();
    }

    public int getAge() {
        DateTime date = new DateTime();
        DateTime birthDate = new DateTime(this.birthDate);
        Period period = new Period(birthDate,date);

        return period.getYears();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Set<Contact> getFriendList() {
        return friendList;
    }

    public void setFriendList(Set<Contact> friendList) {
        this.friendList = friendList;
    }

    public Set<Chat> getConversation() {
        return conversation;
    }

    public void setConversation(Set<Chat> conversation) {
        this.conversation = conversation;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + super.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

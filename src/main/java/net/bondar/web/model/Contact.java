package net.bondar.web.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Azeral on 28.10.2015.
 */
@Entity
@Table(name = "CONTACT")
public class Contact extends AbstractEntity{

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @ManyToMany
    private Set<Hobby> hobbies;

    @ManyToMany
    private Set<Place> places;

    @ManyToMany
    private Set<Contact> friendList;

    @OneToMany
    private Set<Chat> conversation;

    @OneToMany
    private Set<Post> posts;


    public Contact(){
        super();
    }

    public Contact(long id){
        super(id);
    }

    public Contact(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.hobbies = new HashSet<>();
        this.places = new HashSet<>();
        this.friendList = new HashSet<>();
        this.conversation = new HashSet<>();
        this.posts = new HashSet<>();
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
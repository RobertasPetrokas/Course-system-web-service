package com.example.nesvarbu.classList;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Person extends User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    public Person(String login, String password, LocalDate dateCreated, LocalDate dateModified, boolean isActive, String firstName, String lastName, String email, String phoneNumber) {
        super(login, password, dateCreated, dateModified, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public Person(String login, String password, String firstName, String lastName, String email, String phoneNumber) {
        super(login, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}

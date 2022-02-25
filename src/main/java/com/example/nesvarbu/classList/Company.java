package com.example.nesvarbu.classList;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Company extends User implements Serializable {
    private String companyName;
    private String representative;
    private String phoneNumber;
    private String email;

    public Company(String login, String password, LocalDate dateCreated, LocalDate dateModified, boolean isActive, String companyName, String representative, String phoneNumber, String email) {
        super(login, password, dateCreated, dateModified, isActive);
        this.companyName = companyName;
        this.representative = representative;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Company(String login, String password, String companyName, String representative, String phoneNumber, String email) {
        super(login, password);
        this.companyName = companyName;
        this.representative = representative;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

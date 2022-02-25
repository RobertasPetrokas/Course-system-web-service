package com.example.nesvarbu.classList;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Roles role;

    @OneToMany(mappedBy = "createdBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference
    private List<File> myFiles;


    @OneToMany(mappedBy = "createdBy", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference
    private List<Course> myCreatedCourses;

    @ManyToMany
    @JsonBackReference
    private List<Course> myModeratedCourses;

    @ManyToMany
    @JsonBackReference
    private List<Folder> myFolders;

    @ManyToMany
    @JsonBackReference
    private List<Course> myCourses;

    private String login;
    private String password;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private boolean isActive = false;

    public User() {
    }

    public User(String login, String password, LocalDate dateCreated, LocalDate dateModified, boolean isActive) {
        this.login = login;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.isActive = isActive;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.isActive = true;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Course> getMyModeratedCourses() {
        return myModeratedCourses;
    }

    public void setMyModeratedCourses(List<Course> myModeratedCourses) {
        this.myModeratedCourses = myModeratedCourses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Folder> getMyFolders() {
        return myFolders;
    }

    public void setMyFolders(List<Folder> myFolders) {
        this.myFolders = myFolders;
    }

    public List<File> getMyFiles() {
        return myFiles;
    }

    public void setMyFiles(List<File> myFiles) {
        this.myFiles = myFiles;
    }

    public List<Course> getMyCreatedCourses() {

        return myCreatedCourses;
    }

    public void setMyCreatedCourses(List<Course> myCreatedCourses) {
        this.myCreatedCourses = myCreatedCourses;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    @Override
    public String toString() {
        return login;
    }
}

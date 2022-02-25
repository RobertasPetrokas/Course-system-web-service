package com.example.nesvarbu.classList;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Folder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "myFolders", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> moderators;

    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> subfolders;

    @OneToMany(mappedBy = "folder", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> files;

    @ManyToOne
    @JsonBackReference
    private Folder parentFolder;


    @ManyToOne
    private User createdBy;

    @ManyToOne
    @JsonBackReference
    private Course parentCourse;

    private String folderName;
    private LocalDate dateCreated;
    private LocalDate dateModified;


    public Folder() {
    }

    public Folder(String folderName, List<User> moderators, List<Folder> subfolders, List<File> files, LocalDate dateCreated, LocalDate dateModified, User createdBy) {
        this.folderName = folderName;
        this.moderators = moderators;
        this.subfolders = subfolders;
        this.files = files;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.createdBy = createdBy;
    }

    public Folder(int id, String folderName, List<User> moderators, List<Folder> subfolders, List<File> files, User createdBy) {
        this.id = id;
        this.folderName = folderName;
        this.moderators = moderators;
        this.subfolders = subfolders;
        this.files = files;
        this.createdBy = createdBy;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Folder(String folderName) {
        this.folderName = folderName;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Folder(String folderName, Course parentCourse, Folder parentFolder, User user) {
        this.folderName = folderName;
        this.parentCourse = parentCourse;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.createdBy = user;
        this.moderators = new ArrayList<>();
        this.moderators.add(user);
        this.parentFolder = parentFolder;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public List<Folder> getSubfolders() {
        return subfolders;
    }

    public void setSubfolders(List<Folder> subfolders) {
        this.subfolders = subfolders;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Course getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(Course parentCourse) {
        this.parentCourse = parentCourse;
    }


    @Override
    public String toString() {
        return folderName;
    }
}

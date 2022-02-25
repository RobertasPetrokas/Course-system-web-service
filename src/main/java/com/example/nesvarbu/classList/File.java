package com.example.nesvarbu.classList;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Folder folder;

    private String fileName;
    private LocalDate dateCreated;
    private LocalDate dateModified;

    public File(int id, String fileName, Folder folder, LocalDate dateCreated, LocalDate dateModified, User createdBy) {
        this.id = id;
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.createdBy = createdBy;
        this.folder = folder;
    }

    public File(String fileName, Course course, Folder parentFolder, User user) {
        this.fileName = fileName;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
        this.createdBy = user;
        this.folder = parentFolder;


    }


    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return fileName;
    }
}

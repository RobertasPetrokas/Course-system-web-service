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
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    @ManyToOne
    @JsonBackReference
    private User createdBy;
    @JsonBackReference
    private LocalDate dateCreated;
    @JsonBackReference
    private LocalDate dateModified;
    private String courseDescription;
    @ManyToMany(mappedBy = "myModeratedCourses", cascade = {CascadeType.PERSIST})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference
    private List<User> moderators;
    @ManyToMany(mappedBy = "myCourses", cascade = {CascadeType.PERSIST})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference
    private List<User> students;

    @OneToMany(mappedBy = "parentCourse", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> courseFolders;

    public Course() {
    }


    public Course(int id, String courseName, User createdBy, ArrayList<User> moderators, List<User> students, LocalDate dateCreated, LocalDate dateModified, String courseDescription, ArrayList<Folder> courseFolders) {
        this.id = id;
        this.courseName = courseName;
        this.createdBy = createdBy;
        this.moderators = moderators;
        this.students = students;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.courseDescription = courseDescription;
        this.courseFolders = courseFolders;
    }

    public Course(String courseName, List<User> moderators, List<User> students, String courseDescription, ArrayList<Folder> courseFolders, LocalDate dateCreated, LocalDate dateModified) {
        this.courseName = courseName;
        this.moderators = moderators;
        this.students = students;
        this.courseDescription = courseDescription;
        this.courseFolders = courseFolders;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Course(String courseName, List<User> moderators, List<User> students, String courseDescription, ArrayList<Folder> courseFolders) {
        this.courseName = courseName;
        this.moderators = moderators;
        this.students = students;
        this.courseDescription = courseDescription;
        this.courseFolders = courseFolders;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Course(String courseName, String courseDescription, User user) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.createdBy = user;
        this.students = new ArrayList<>();
        this.moderators = new ArrayList<>();
        this.moderators.add(user);
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.students = new ArrayList<>();
        this.moderators = new ArrayList<>();
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<Folder> getCourseFolders() {
        return courseFolders;
    }

    public void setCourseFolders(List<Folder> courseFolders) {
        this.courseFolders = courseFolders;
    }


    @Override
    public String toString() {
        return courseName;
    }

    public void addStudent(User user) {
        this.students.add(user);
    }
}

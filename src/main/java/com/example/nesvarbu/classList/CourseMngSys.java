package com.example.nesvarbu.classList;

import java.io.Serializable;
import java.util.List;

public class CourseMngSys implements Serializable {
    int id;
    private List<Course> allCourses;
    private List<User> allUsers;

    public CourseMngSys(int id, List<Course> allCourses, List<User> allUsers) {
        this.id = id;
        this.allCourses = allCourses;
        this.allUsers = allUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(List<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}

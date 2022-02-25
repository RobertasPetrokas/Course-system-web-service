package com.example.nesvarbu.repos;

import com.example.nesvarbu.classList.Course;
import com.example.nesvarbu.classList.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

//    public String getCourseByCourseDescriptionAndAndCourseName();
}
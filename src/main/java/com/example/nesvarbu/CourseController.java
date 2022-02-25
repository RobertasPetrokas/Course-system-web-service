package com.example.nesvarbu;

import com.example.nesvarbu.classList.Course;
import com.example.nesvarbu.classList.Person;
import com.example.nesvarbu.classList.User;
import com.example.nesvarbu.repos.CourseRepository;
import com.example.nesvarbu.repos.PersonRepository;
import com.example.nesvarbu.repos.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/getAllCourses")
    public @ResponseBody
    List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(value = "/getCourse/{id}")
    public @ResponseBody
    Optional<Course> getCourseById(@PathVariable int id) {
        return courseRepository.findById(id);
    }

    @PostMapping(value = "/addCourse")
    public @ResponseBody
    String addCourse(@RequestBody String courseInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(courseInfo, Properties.class);
        Course course = new Course(properties.getProperty("courseName"), properties.getProperty("courseDescription"));
        courseRepository.save(course);
        return "Success";
    }

    @DeleteMapping(value = "/deleteCourse/{id}")
    public @ResponseBody
    String deleteCourse(@PathVariable int id) {
        Course course = courseRepository.getById(id);

        courseRepository.delete(course);
        return "Course deleted";
    }

    @PutMapping(value = "/updateCourse/{id}")
    public @ResponseBody
    String updateUser(@RequestBody String request, @PathVariable int id) {

        Course course = courseRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        if(properties.getProperty("courseName") != null) {
            course.setCourseName(properties.getProperty("courseName"));
        }
        if(properties.getProperty("courseDescription") != null) {
            course.setCourseDescription(properties.getProperty("courseDescription"));
        }

        //Likusius pabaigti

        courseRepository.save(course);
        return "Test";

    }

    @PutMapping(value = "/enroll/{id}")
    public @ResponseBody
    String enrollUser(@RequestBody String request, @PathVariable int id) {

        Course course = courseRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        User user = null;
        user = userRepository.getById(Integer.parseInt(properties.getProperty("userId")));

        course.getStudents().add(user);
        user.getMyCourses().add(course);

        courseRepository.save(course);
        userRepository.save(user);
        return "Sucess";

    }

    @PutMapping(value = "/unroll/{id}")
    public @ResponseBody
    String unrollUser(@RequestBody String request, @PathVariable int id) {

        Course course = courseRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        User user = null;
        user = userRepository.getById(Integer.parseInt(properties.getProperty("userId")));

        course.getStudents().remove(user);
        user.getMyCourses().remove(course);

        courseRepository.save(course);
        userRepository.save(user);
        return "Sucess";

    }
}

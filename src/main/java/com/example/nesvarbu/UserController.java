package com.example.nesvarbu;

import com.example.nesvarbu.classList.*;
import com.example.nesvarbu.repos.CompanyRepository;
import com.example.nesvarbu.repos.CourseRepository;
import com.example.nesvarbu.repos.PersonRepository;
import com.example.nesvarbu.repos.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class UserController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/getAllUsers")
    public @ResponseBody
    List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PostMapping(value = "/userLogin")
    public @ResponseBody User loginUser(@RequestBody String request) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Person person = null;
        Company company = null;

        if(properties.getProperty("type").equals("Person")){
            person = personRepository.getPersonByLoginAndPassword(properties.getProperty("username"), properties.getProperty("password"));
            return person;
        } else if(properties.getProperty("type").equals("Company")){
            company = companyRepository.getCompanyByLoginAndPassword(properties.getProperty("username"), properties.getProperty("password"));
            return company;
        }

        return null;
    }

    @GetMapping(value = "/getMyCourses/{id}")
    public @ResponseBody List <Course> getMyCourses(@PathVariable int id) {

        User user = null;
        user = userRepository.getById(id);

        return user.getMyCourses();
    }




}

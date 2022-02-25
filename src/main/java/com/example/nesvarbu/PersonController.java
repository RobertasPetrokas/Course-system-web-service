package com.example.nesvarbu;

import com.example.nesvarbu.classList.Person;
import com.example.nesvarbu.repos.PersonRepository;
import com.example.nesvarbu.repos.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/getAllPersons")
    public @ResponseBody
    List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/getPerson/{id}")
    public @ResponseBody
    Optional<Person> getPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @PostMapping(value = "/addPerson")
    public @ResponseBody
    String addPerson(@RequestBody String personInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(personInfo, Properties.class);
        Person person = new Person(properties.getProperty("login"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("sname"), properties.getProperty("email"), properties.getProperty("card"));
        userRepository.save(person);
        return "Success";
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @PutMapping(value = "/updatePerson/{id}")
    public @ResponseBody
    String updateUser(@RequestBody String request, @PathVariable int id) {

        Person person = personRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        if(properties.getProperty("firstName") != null)
        {
            person.setFirstName(properties.getProperty("firstName"));
        }
        if(properties.getProperty("lastName") != null)
        {
            person.setLastName(properties.getProperty("lastName"));
        }
        if(properties.getProperty("email") != null)
        {
            person.setEmail(properties.getProperty("email"));
        }
        if(properties.getProperty("phoneNumber") != null)
        {
            person.setPhoneNumber(properties.getProperty("phoneNumber"));
        }

        person.setDateModified(LocalDate.now());



        userRepository.save(person);
        return "Test";

    }
}

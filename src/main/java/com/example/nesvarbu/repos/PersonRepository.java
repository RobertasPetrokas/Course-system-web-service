package com.example.nesvarbu.repos;

import com.example.nesvarbu.classList.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person getPersonByLoginAndPassword(String login, String password);
}

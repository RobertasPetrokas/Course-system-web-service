package com.example.nesvarbu.repos;

import com.example.nesvarbu.classList.Company;
import com.example.nesvarbu.classList.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    public Company getCompanyByLoginAndPassword(String login, String password);
}

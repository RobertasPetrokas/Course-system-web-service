package com.example.nesvarbu;

import com.example.nesvarbu.classList.Company;
import com.example.nesvarbu.classList.Person;
import com.example.nesvarbu.repos.CompanyRepository;
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
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/getAllCompanies")
    public @ResponseBody
    List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/getCompany/{id}")
    public @ResponseBody
    Optional<Company> getCompanyById(@PathVariable int id) {
        return companyRepository.findById(id);
    }

    @PostMapping(value = "/addCompany")
    public @ResponseBody
    String addCompany(@RequestBody String companyInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(companyInfo, Properties.class);
        Company company = new Company();
        userRepository.save(company);
        return "Success";
    }

    @DeleteMapping(value = "/deleteCompany/{id}")
    public @ResponseBody
    String deleteCompany(@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @PutMapping(value = "/updateCompany/{id}")
    public @ResponseBody
    String updateCompany(@RequestBody String request, @PathVariable int id) {

        Company company = companyRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        if(properties.getProperty("companyName") != null)
        {
            company.setCompanyName(properties.getProperty("companyName"));
        }
        if(properties.getProperty("representative") != null)
        {
            company.setRepresentative(properties.getProperty("representative"));
        }
        if(properties.getProperty("email") != null)
        {
            company.setEmail(properties.getProperty("email"));
        }
        if(properties.getProperty("phoneNumber") != null)
        {
            company.setPhoneNumber(properties.getProperty("phoneNumber"));
        }
        company.setDateModified(LocalDate.now());



        userRepository.save(company);
        return "Test";

    }
}

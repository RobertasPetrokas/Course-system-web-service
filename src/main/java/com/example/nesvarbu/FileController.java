package com.example.nesvarbu;


import com.example.nesvarbu.classList.File;
import com.example.nesvarbu.repos.FileRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class FileController {
    @Autowired
    private FileRepository fileRepository;

    @GetMapping(value = "/getAllFiles")
    public @ResponseBody
    List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @GetMapping(value = "/getFile/{id}")
    public @ResponseBody
    Optional<File> getFileById(@PathVariable int id) {
        return fileRepository.findById(id);
    }

    @PostMapping(value = "/addFile")
    public @ResponseBody
    String addFile(@RequestBody String fileInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(fileInfo, Properties.class);
        File file = new File();
        fileRepository.save(file);
        return "Success";
    }

    @DeleteMapping(value = "/deleteFile/{id}")
    public @ResponseBody
    String deleteFile(@PathVariable int id) {
        fileRepository.deleteById(id);
        return "File deleted";
    }

    @PutMapping(value = "/updateFile/{id}")
    public @ResponseBody
    String updateFile(@RequestBody String request, @PathVariable int id) {

        File file = fileRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        if(properties.getProperty("fileName") != null) {
            file.setFileName(properties.getProperty("fileName"));
        }

        file.setDateModified(LocalDate.now());


        //Likusius pabaigti

        fileRepository.save(file);
        return "Test";

    }
}

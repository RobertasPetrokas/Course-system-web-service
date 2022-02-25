package com.example.nesvarbu;


import com.example.nesvarbu.classList.Folder;
import com.example.nesvarbu.repos.FolderRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
public class FolderController {
    @Autowired
    private FolderRepository folderRepository;

    @GetMapping(value = "/getAllFolders")
    public @ResponseBody
    List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    @GetMapping(value = "/getFolder/{id}")
    public @ResponseBody
    Optional<Folder> getFolderById(@PathVariable int id) {
        return folderRepository.findById(id);
    }

    @PostMapping(value = "/addFolder")
    public @ResponseBody
    String addFolder(@RequestBody String folderInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(folderInfo, Properties.class);
        Folder folder = new Folder();
        folderRepository.save(folder);
        return "Success";
    }

    @DeleteMapping(value = "/deleteFolder/{id}")
    public @ResponseBody
    String deleteFolder(@PathVariable int id) {
        folderRepository.deleteById(id);
        return "Folder deleted";
    }

    @PutMapping(value = "/updateFolder/{id}")
    public @ResponseBody
    String updateFolder(@RequestBody String request, @PathVariable int id) {

        Folder folder = folderRepository.getById(id);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        if(properties.getProperty("folderName") != null) {
            folder.setFolderName(properties.getProperty("folderName"));
        }
        folder.setDateModified(LocalDate.now());

        //Likusius pabaigti

        folderRepository.save(folder);
        return "Test";

    }
}

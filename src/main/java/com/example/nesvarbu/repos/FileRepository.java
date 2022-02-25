package com.example.nesvarbu.repos;

import com.example.nesvarbu.classList.File;
import com.example.nesvarbu.classList.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {


}
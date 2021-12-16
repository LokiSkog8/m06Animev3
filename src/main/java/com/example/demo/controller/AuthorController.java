package com.example.demo.controller;


import com.example.demo.domain.dto.ResponseList;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping("/")
    public ResponseEntity<?> findAllAuthors(){

        //return animeRepository.findAll();
        return ResponseEntity.ok().body(new ResponseList(authorRepository.findBy()));
    }
}

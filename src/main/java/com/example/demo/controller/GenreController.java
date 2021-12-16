package com.example.demo.controller;


import com.example.demo.domain.dto.ResponseList;
import com.example.demo.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllGenres() {

        //return animeRepository.findAll();
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy()));
    }
}
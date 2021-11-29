package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ListResult;
import com.example.demo.domain.model.Anime;
import com.example.demo.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<ListResult> findAllAnimes(){

        //return animeRepository.findAll();
        return ResponseEntity.ok().body(ListResult.list(animeRepository.findAll()));
    }

    @PostMapping("/")
    public Anime createAnime(@RequestBody Anime anime){
        return animeRepository.save(anime);
    }


    @GetMapping("/{id}")
    public Object getAnime(@PathVariable UUID id) {
        Anime anime = animeRepository.findById(id).orElse(null);

        if (anime == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessage.message("File not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(anime.name))
                .contentType(MediaType.valueOf(anime.description))
                .contentType(MediaType.valueOf(anime.imageurl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){
        Anime anime = animeRepository.findById(id).orElse(null);
        if(anime == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessage.message("File not found"));
        return ResponseEntity.ok().contentType(MediaType.valueOf(String.valueOf(anime.animeid)))
                .body(ErrorMessage.message("File Deleted"));
    }

}

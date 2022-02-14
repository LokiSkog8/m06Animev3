package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.model.Anime;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.Valoration;
import com.example.demo.domain.model.projection.ProjectionAnime;
import com.example.demo.domain.model.projection.ProjectionValoration;
import com.example.demo.repository.AnimeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ValorationRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired private ValorationRepository valorationRepository;
    @Autowired private UserRepository userRepository;


    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/")
    public ResponseEntity<?> findAllAnimes(){
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy(ProjectionAnime.class)));
    }

    @PostMapping("/")
    public Anime createAnime(@RequestBody Anime anime){
        return animeRepository.save(anime);
    }


    @GetMapping("/{id}")
    public Object getAnime(@PathVariable UUID id) {

        return ResponseEntity.ok().body(animeRepository.findByAnimeid(id, ProjectionAnime.class));
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

    @PostMapping("/{id}/valoration")
    public ResponseEntity<?> addValoration(@PathVariable UUID id, Authentication authentication, @RequestBody double score){
        Anime anime = animeRepository.findById(id).orElse(null);
        User user1 = userRepository.findByUsername(authentication.name());
        if(anime == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessage.message("File not found"));
        if(anime != null){
            if(score<=0 || score>=100) {
                if (user1 != null) {
                    Valoration valoration1 = new Valoration();
                    valoration1.userid = user1.userid;
                    valoration1.animeid = anime.animeid;
                    valoration1.valoration = score;
                    valorationRepository.save(valoration1);
                    return ResponseEntity.ok().build();

                }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("NO AUTORIZADO"));
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ErrorMessage.message("VALOR NO VALIDO"));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("NO EXISTE ESE ANIME"));
    }

    @DeleteMapping("/valoration")
    public ResponseEntity<?> deleteValoration(@PathVariable UUID id, Authentication authentication){
        Anime anime1 = animeRepository.findById(id).orElse(null);
        User user1 = userRepository.findByUsername(authentication.name());
        if(anime1 != null){
            if(user1 != null){
                Valoration valoration = new Valoration();
                valoration.userid = user1.userid;
                valoration.animeid = anime1.animeid;
                valorationRepository.delete(valoration);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("NO AUTORIZADO"));
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("NO ESISTE"));
    }

    @GetMapping("/valoration")
    public ResponseEntity<?> getValorations(@PathVariable UUID id){
        Anime anime = animeRepository.findById(id).orElse(null);
        if(anime!=null){
            return ResponseEntity.ok().body(animeRepository.findByAnimeid(anime.animeid, ProjectionValoration.class));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("NO ESISTE JOE"));
    }
}

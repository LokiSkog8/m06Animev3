package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.RequestedFavorite;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.dto.UserRegisterRequest;
import com.example.demo.domain.model.Favorite;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projection.ProjectionAnime;
import com.example.demo.domain.model.projection.ProjectionFavorite;
import com.example.demo.domain.model.projection.ProjectionUser;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static jdk.internal.net.http.common.Utils.encode;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private FavoriteRepository favoriteRepository;
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(new ResponseList(userRepository.findBy()));
    }

    @PostMapping(path = "/register" )
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByUsername(userRegisterRequest.username) == null) {
            User user = new User();
            user.username = userRegisterRequest.username;
            user.password = encode(userRegisterRequest.password);
            user.enabled = true;
            return ResponseEntity.ok().body(userRepository.save(user).userid.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.message("Nom d'usuari no disponible"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findUserById(@PathVariable UUID id){
        return ResponseEntity.ok().body(userRepository.findByUserid(id, ProjectionUser.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessage.message("File not found"));
        return ResponseEntity.ok().contentType(MediaType.valueOf(String.valueOf(user.userid)))
                .body(ErrorMessage.message("File Deleted"));
    }



    @PostMapping("/favorites")
    public ResponseEntity<?> addFavorite(@RequestBody RequestedFavorite requestedFavorite, Authentication authentication){

        if(authentication != null){
            User userAutenticated = userRepository.findByUsername(authentication.getName());
            if(userAutenticated != null){
                Favorite favorite = new Favorite();
                favorite.userid = userAutenticated.userid;
                favorite.animeid = requestedFavorite.animeid;
                favoriteRepository.save(favorite);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("Pitos"));
        
    }

    @DeleteMapping("/favorites")
    public ResponseEntity<?> delFavorites(@RequestBody RequestedFavorite requestedFavorite, Authentication authentication){
        if(authentication != null){
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if(authenticatedUser != null){
                Favorite favorite = new Favorite();
                favorite.userid= authenticatedUser.userid;
                favorite.animeid = requestedFavorite.animeid;
                favoriteRepository.delete(favorite);
                System.out.println("DONE");
                return ResponseEntity.ok().build();

            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("Que no"));
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> getFavorites(Authentication authentication){
        if(authentication != null){
            User authenticadedUser = userRepository.findByUsername(authentication.getName());
            if(authenticadedUser != null){
                return ResponseEntity.ok().body(userRepository.findByUsername(authentication.getName(), ProjectionFavorite.class));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("pesao"));
    }

}
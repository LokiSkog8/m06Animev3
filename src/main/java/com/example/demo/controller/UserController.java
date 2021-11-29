package com.example.demo.controller;
import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.UserRegisterRequest;
import com.example.demo.domain.model.Anime;
import com.example.demo.domain.model.File;
import com.example.demo.domain.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static jdk.internal.net.http.common.Utils.encode;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserRepository userRepository;

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

    @GetMapping
    public List<User> getALl(){
        return userRepository.findAll();
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


    @DeleteMapping
    public void deleteUsers(){
            }

    // WEB REGISTER FORM (for testing)
    @GetMapping("/register/web")
    public String hack(){
        return "<div style='display:flex;flex-direction:column;width:20em;gap:0.5em'>" +
                "<input name='username' id='username' placeholder='Username'>" +
                "<input id='password' type='password' placeholder='Password'>" +
                "<input type='button' value='Register' onclick='fetch(\"/users/register/\",{method:\"POST\",headers:{\"Content-Type\":\"application/json\"},body:`{\"username\":\"${username.value}\",\"password\":\"${password.value}\"}`})'></div>";
    }
}
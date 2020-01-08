package com.webstore.trill.controller;

import com.webstore.trill.Entity.UserEntity;
import com.webstore.trill.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid final UserEntity newUser){
        if(userRepo.existsByName(newUser.getName())){
            userRepo.save(newUser);
            return ResponseEntity.ok("User created");
        }

    }
}

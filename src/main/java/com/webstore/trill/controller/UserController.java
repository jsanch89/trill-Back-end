package com.webstore.trill.controller;

import com.webstore.trill.Entity.SellEntity;
import com.webstore.trill.Entity.UserEntity;
import com.webstore.trill.HttpModels.SellRequest;
import com.webstore.trill.Repository.SellRepository;
import com.webstore.trill.Repository.UserRepository;
import com.webstore.trill.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid final UserEntity newUser){
        if(!userRepo.existsByName(newUser.getName())){
            userRepo.save(newUser);
            return ResponseEntity.ok("User created");
        }
        throw new RuntimeException("Register invalid");
    }




}

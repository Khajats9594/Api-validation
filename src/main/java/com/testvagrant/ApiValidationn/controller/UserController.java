package com.testvagrant.ApiValidationn.controller;

import com.testvagrant.ApiValidationn.dto.UserRequest;
import com.testvagrant.ApiValidationn.entity.User;
import com.testvagrant.ApiValidationn.exception.UserNotFoundException;
import com.testvagrant.ApiValidationn.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest){
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable @Positive(message = "User ID must be a positive number") int id) throws UserNotFoundException {
       return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable @Positive(message = "User ID must be a positive number") int id, @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {
            User updatedUser = userService.updateUser(id, userRequest);
            return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable @Positive(message = "User ID must be a positive number") int id) throws UserNotFoundException {
            userService.deleteUser(id);
        return "User with ID " + id + " has been successfully deleted.";
    }

}

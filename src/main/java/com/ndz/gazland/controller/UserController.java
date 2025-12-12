package com.ndz.gazland.controller;


import com.ndz.gazland.models.User;
import com.ndz.gazland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity addUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/")
    public ResponseEntity readAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.readUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity readUserByID(@PathVariable int id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.readUserByID(id));
    }



}

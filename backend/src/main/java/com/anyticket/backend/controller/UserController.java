package com.anyticket.backend.controller;

import com.anyticket.backend.domain.User;
import com.anyticket.backend.dto.RegisterUserDto;
import com.anyticket.backend.dto.UserDto;
import com.anyticket.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Set<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto user) {
        Optional<User> registeredUser = userService.save(user);
        if (registeredUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new UserDto(registeredUser.get()), HttpStatus.CREATED);
    }

}

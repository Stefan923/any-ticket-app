package com.anyticket.backend.controller;

import com.anyticket.backend.domain.User;
import com.anyticket.backend.dto.RegisterUserDto;
import com.anyticket.backend.dto.UserDto;
import com.anyticket.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Set<UserDto>> getUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(userService.findAll(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDto user) {
        try {
            Optional<User> registeredUser = userService.save(user);
            if (registeredUser.isPresent()) {
                return new ResponseEntity<>(new UserDto(registeredUser.get()), HttpStatus.CREATED);
            }
        } catch (UnexpectedRollbackException ignored) { }

        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}

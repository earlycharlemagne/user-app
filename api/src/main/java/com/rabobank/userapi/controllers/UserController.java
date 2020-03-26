package com.rabobank.userapi.controllers;

import com.rabobank.userapi.exceptions.UserNotFoundException;
import com.rabobank.userapi.models.User;
import com.rabobank.userapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:8000")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @GetMapping("/users")
    public ResponseEntity<User> getUser(@RequestParam(value = "firstname") String firstname,
                                        @RequestParam(value = "surname") String surname) {
        try {
            return new ResponseEntity<User>(userService.getUserByName(firstname, surname), HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}

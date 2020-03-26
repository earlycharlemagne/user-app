package com.rabobank.userapi.controllers;

import com.rabobank.userapi.models.User;
import com.rabobank.userapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @GetMapping("/users")
    public ResponseEntity<User> getUser(@RequestParam(value = "firstname") String firstname,
                                        @RequestParam(value = "surname") String surname) {

        return new ResponseEntity<User>(userService.getUserByName(firstname, surname), HttpStatus.OK);
    }
}

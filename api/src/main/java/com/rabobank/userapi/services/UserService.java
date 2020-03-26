package com.rabobank.userapi.services;

import com.rabobank.userapi.exceptions.UserNotFoundException;
import com.rabobank.userapi.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final static String ENDPOINT = "http://localhost:5000/users";
    private RestTemplate restTemplate;

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    public User getUserByName(String firstname, String surname) throws Exception {
        String url = ENDPOINT + "?firstname=" + firstname + "&lastname=" + surname;
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        HttpStatus responseStatusCode = response.getStatusCode();

        if (responseStatusCode != HttpStatus.OK) {
            if (responseStatusCode == HttpStatus.NOT_FOUND)
                throw new UserNotFoundException();
            else
                throw new Exception("User Service Exception. Unable to request user.");
        }

        return response.getBody();
    }
}

package com.rabobank.userapi.services;

import com.rabobank.userapi.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final static String ENDPOINT = "http://localhost:5000/users";
    private RestTemplate restTemplate;

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    public User getUserByName(String firstname, String surname) {
        String url = ENDPOINT + "?firstname=" + firstname + "&lastname=" + surname;

        return restTemplate.getForObject(url, User.class);
    }
}

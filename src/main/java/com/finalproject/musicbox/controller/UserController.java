package com.finalproject.musicbox.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    // Controller class for handling user-related requests.
    // This class will define the endpoints for user registration, login, and other user-related operations.
}

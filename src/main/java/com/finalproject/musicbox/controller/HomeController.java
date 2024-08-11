package com.finalproject.musicbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling requests to the home and registration pages.
 */
@Controller
public class HomeController {

    /**
     * Handles GET requests to the /register endpoint.
     *
     * @return the name of the HTML file for the registration form (register.html)
     */
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // The name of the HTML file in the templates directory (register.html)
    }

    /**
     * Handles GET requests to the root endpoint (/).
     *
     * @return the name of the HTML file for the home page (index.html)
     */
    @GetMapping("/")
    public String index() {
        return "index";  // The name of the HTML file (index.html)
    }
}
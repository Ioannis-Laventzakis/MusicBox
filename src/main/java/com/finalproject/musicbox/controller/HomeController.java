package com.finalproject.musicbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // The name of the HTML file in the templates directory (register.html)
    }

    @GetMapping("/")
    public String index() {
        return "index";  // The name of the HTML file (index.html)
    }
}

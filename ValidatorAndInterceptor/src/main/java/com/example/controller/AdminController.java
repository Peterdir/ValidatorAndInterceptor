package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String home() {
        return "admin-home";
    }

    @GetMapping("/category")
    public String category() {
        return "category";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }
}


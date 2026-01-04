package com.myDreamBook.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myDreamBook.service.UserService;


@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my_dream_book/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/my_dream_book/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {

        boolean success = userService.registerUser(username, password);

        if (!success) {
            model.addAttribute("error",
                    "Username already exists. Please login.");
            return "register"; // stay on register page
        }

        model.addAttribute("success",
                "Registration successful! Please login.");
        return "login";
    }
    
    @GetMapping("/my_dream_book/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/my_dream_book/login")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        boolean success = userService.verifyUserLogin(username, password);

        if (!success) {
            model.addAttribute("error", "Incorrect username or password.");
            return "login";
        }

        model.addAttribute("success", "Login successful!");
        return "redirect:/my_dream_book/home";
    }

}

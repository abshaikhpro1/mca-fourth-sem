package com.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    // Mapping for the login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Mapping for the user profile page
    @GetMapping("/profile")
    public String profilePage(Model model) {
        // Retrieve the username from the authenticated user (e.g., from session or security context)
        String username = "JohnDoe"; // Replace with your logic to get the username
        model.addAttribute("username", username);
        return "profile";
    }

    // Mapping for the logout action
    @GetMapping("/logout")
    public String logout() {
        // Handle the logout logic (e.g., invalidate session or clear security context)
        return "redirect:/login"; // Redirect to the login page after logout
    }

    // Handle the login form submission
    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        // Validate the username and password (e.g., authenticate against a user database)
        // If valid, redirect to the user profile page
        // If invalid, show an error message and return to the login page
        return "redirect:/profile";
    }
}

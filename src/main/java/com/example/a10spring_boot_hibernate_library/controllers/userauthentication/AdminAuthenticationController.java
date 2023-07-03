package com.example.a10spring_boot_hibernate_library.controllers.userauthentication;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.UserAuthentication;
import com.example.a10spring_boot_hibernate_library.services.UserAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Enumeration;
import java.util.Map;

@Controller
@SessionAttributes("authenticatedClient")
public class AdminAuthenticationController {
    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public AdminAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/adminlogin")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println("Processing login request...");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        UserAuthentication userAuthentication = userAuthenticationService.authenticateUser(username, password);

        if (userAuthentication != null) {
            // User authentication succeeded
            Client client = userAuthentication.getClientByClientId();
            model.addAttribute("authenticatedClient", client);
            System.out.println("User authentication succeeded. Client: " + client.getFirstName());


            return "admin";  // Redirect to the home page after successful login
        } else {
            // User authentication failed
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            System.out.println("User authentication failed. Invalid username or password.");
            return "adminlogin";  // Redirect back to the login page with an error message
        }
    }

    @Autowired
    private HttpServletRequest request;
    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("authenticatedClient", null);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/adminlogin.html";
    }

}

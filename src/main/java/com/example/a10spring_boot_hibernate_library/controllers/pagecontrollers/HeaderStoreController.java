package com.example.a10spring_boot_hibernate_library.controllers.pagecontrollers;


import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HeaderStoreController {

    private final UserAuthenticationService userAuthenticationService;

    @Autowired
    public HeaderStoreController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/headerstore")
    public String getPage(@SessionAttribute(value = "authenticatedClient", required = false) Client authenticatedClient, Model model) {
        if (authenticatedClient != null) {
            model.addAttribute("authenticatedClient", authenticatedClient);
        }
        return "headerstore";
    }
}

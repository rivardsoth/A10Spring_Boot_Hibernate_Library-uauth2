package com.example.a10spring_boot_hibernate_library.controllers.shoppingcart;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ShoppingCart;
import com.example.a10spring_boot_hibernate_library.services.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    @Autowired
    private HttpServletRequest request;
    @GetMapping("/shoppingcart")
    public String viewShoppingCart(Model model) {
        // Retrieve authenticated client from session
        HttpSession session = request.getSession();
        Client authenticatedClient = (Client) session.getAttribute("authenticatedClient");
        model.addAttribute("authenticatedClient", authenticatedClient);


        // Get the shopping carts for the authenticated client
        List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCartsByClientId(authenticatedClient.getClientId());

        model.addAttribute("shoppingCarts", shoppingCarts);

        return "shoppingcart";
    }
}

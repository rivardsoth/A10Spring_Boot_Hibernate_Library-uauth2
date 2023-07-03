package com.example.a10spring_boot_hibernate_library.controllers.shoppingcart;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ShoppingCart;
import com.example.a10spring_boot_hibernate_library.services.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartAddController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartAddController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("isbn") Long isbn, Model model) {

        HttpSession session = request.getSession();
        Client authenticatedClient = (Client) session.getAttribute("authenticatedClient");

        Integer clientId = authenticatedClient.getClientId();

        if (clientId == null) {
            // Handle the case where client ID is not available in the session
            // You can redirect to a login page or perform other appropriate actions
            throw new RuntimeException("Client ID not found in session");
        }

        // Create a new shopping cart item
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setClientId(clientId);
        shoppingCart.setEanIsbn13(isbn);
        shoppingCart.setQuantity(1); // Set the quantity as needed

        // Save the shopping cart item
        shoppingCartService.saveShoppingCart(shoppingCart);

        // Set the confirmation message in the model
        model.addAttribute("message", "Added to cart: " + isbn);

        return "confirmation";
    }
}

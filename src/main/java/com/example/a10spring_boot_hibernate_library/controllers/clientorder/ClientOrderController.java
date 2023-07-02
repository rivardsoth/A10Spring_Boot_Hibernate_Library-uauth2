package com.example.a10spring_boot_hibernate_library.controllers.clientorder;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientOrderController {

    private final ClientOrderService clientOrderService;

    @Autowired
    public ClientOrderController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @GetMapping("/client-orders")
    public String getClientOrders(Model model) {
        List<ClientOrder> clientOrders = clientOrderService.getAllClientOrders();
        model.addAttribute("clientOrders", clientOrders);
        return "clientorders";
    }
}

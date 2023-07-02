package com.example.a10spring_boot_hibernate_library.controllers.orderitem;

import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderItemByIDController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemByIDController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/order-items-by-orderid")
    public String getOrderItemsByOrderId(@RequestParam("orderId") int orderId, Model model) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        model.addAttribute("orderItems", orderItems);
        return "orderitems"; // Assuming you have a corresponding Thymeleaf template named "order-items.html"
    }
}

package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ShoppingCart;
import com.example.a10spring_boot_hibernate_library.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> getShoppingCartById(int id) {
        return shoppingCartRepository.findById(id);
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCartById(int id) {
        shoppingCartRepository.deleteById(id);
    }

    /*
    public ShoppingCart getShoppingCartByClientId(int clientId) {
        return shoppingCartRepository.findByClientId(clientId);
    }
    */


    public List<ShoppingCart> getShoppingCartsByClientId(int clientId) {
        return shoppingCartRepository.findAllByClientId(clientId);
    }
}

package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientOrderService {

    private final ClientOrderRepository clientOrderRepository;

    @Autowired
    public ClientOrderService(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }

    public List<ClientOrder> getAllClientOrders() {
        return clientOrderRepository.findAll();
    }

    public Optional<ClientOrder> getClientOrderById(int id) {
        return clientOrderRepository.findById(id);
    }

    public ClientOrder saveClientOrder(ClientOrder clientOrder) {
        return clientOrderRepository.save(clientOrder);
    }

    public void deleteClientOrderById(int id) {
        clientOrderRepository.deleteById(id);
    }
}

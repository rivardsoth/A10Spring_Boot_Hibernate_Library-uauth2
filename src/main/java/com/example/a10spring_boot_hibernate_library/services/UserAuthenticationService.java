package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.UserAuthentication;
import com.example.a10spring_boot_hibernate_library.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private final UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    public UserAuthenticationService(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    public UserAuthentication authenticateUser(String username, String password) {
        // Retrieve the user authentication record from the database based on the provided username
        UserAuthentication userAuthentication = userAuthenticationRepository.findByUsername(username);

        if (userAuthentication != null && userAuthentication.getPassword().equals(password)) {
            // User is authenticated
            return userAuthentication;
        }

        // User authentication failed
        return null;
    }
}

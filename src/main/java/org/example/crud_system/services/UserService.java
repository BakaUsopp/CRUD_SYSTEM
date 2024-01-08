package org.example.crud_system.services;

import org.example.crud_system.entities.User;
import org.example.crud_system.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<?> signUp(User user) {
        User existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser != null) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
package org.example.crud_system.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface TokenGenerator {


    String extractUserName(String token);


    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}

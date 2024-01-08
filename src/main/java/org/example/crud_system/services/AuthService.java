package org.example.crud_system.services;


import org.example.crud_system.response.JwtAuthenticationResponse;

public interface AuthService {

    JwtAuthenticationResponse signup(String email, String password);
    JwtAuthenticationResponse signin(String email, String password);



    }
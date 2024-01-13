package org.example.crud_system.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private int id;
    private String username;
    private String password;
    private String role;
    private String token;
}

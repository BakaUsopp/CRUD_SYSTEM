package org.example.crud_system.controllers;

import org.example.crud_system.services.TokenGenerator;
import org.example.crud_system.entities.User;
import org.example.crud_system.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;
    private final TokenGenerator tokenGenerator;

    public UserController(UserServiceImpl userService, TokenGenerator tokenGenerator) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/rgs")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

//    @GetMapping("/lgn/{email}/{password}")
//    public ResponseEntity<?> loginUser(@PathVariable(value = {"email","pass"}) String email, @PathVariable String password){
//        Optional<User> user = Optional.ofNullable(userService.getUserByEmail(email));
//        return user.map(value -> {
//            if (value.getPassword().equals(password)){
//                return new ResponseEntity<>(tokenGenerator.generateToken(value), HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }



    @GetMapping("/lgn/{id}")
    public ResponseEntity<String> loginUser(@PathVariable(value = "id") Long id){
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        return user.map(value -> new ResponseEntity<>(tokenGenerator.generateToken(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lgn/{email}")
    public ResponseEntity<String> loginUser(@PathVariable(value = "email") String email){
        Optional<User> user = Optional.ofNullable(userService.getUserByEmail(email));
        return user.map(value -> new ResponseEntity<>(tokenGenerator.generateToken(value)
                , HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/upd/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user){
        Optional<User> user1 = Optional.ofNullable(userService.getUserById(id));
        return user1.map(value -> new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        return user.map(value -> {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
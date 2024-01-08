package org.example.crud_system.controllers;


import org.example.crud_system.services.TokenGenerator;
import org.example.crud_system.entities.User;
import org.example.crud_system.services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;
    private final TokenGenerator tokenGenerator;

    public UserController(UserServiceImpl userService, TokenGenerator tokenGenerator) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
    }


    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user){
//        try{
//            User user1 = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
//            return new ResponseEntity<>(tokenGenerator.generateToken(user1), HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestBody User user){
        try{
            User user1 = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try{
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        try{
            userService.deleteUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

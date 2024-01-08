package org.example.crud_system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/Unsecured")
    public ResponseEntity<?> getTest(){
        return new ResponseEntity<>("Normal msg....", org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/Secured")
    public ResponseEntity<?> getTestSecured(){
        return new ResponseEntity<>("Secured msg....", org.springframework.http.HttpStatus.OK);
    }


}

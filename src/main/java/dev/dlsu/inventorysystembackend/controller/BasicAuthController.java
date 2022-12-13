package dev.dlsu.inventorysystembackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/basicauth")
public class BasicAuthController {
    
    @GetMapping
    public String authenticate() {
        return "Authenticated";
    }
    
}

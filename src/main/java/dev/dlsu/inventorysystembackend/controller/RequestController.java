package dev.dlsu.inventorysystembackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.dlsu.inventorysystembackend.model.Request;
import dev.dlsu.inventorysystembackend.service.RequestService;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    
    @GetMapping
    public List<Request> findAll() {
        return requestService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<String> addRequest(@RequestBody Request request) {
        return requestService.saveRequest(request);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequest(@RequestBody Request request, @PathVariable Long id) {
        return requestService.editRequest(request, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        return requestService.deleteRequest(id);
    }
}

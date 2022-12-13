package dev.dlsu.inventorysystembackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:3000")
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
        return requestService.makeRequest(request);
    }
    
    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequest(@RequestBody Request request, @PathVariable Long id) {
        return requestService.editRequest(request, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        return requestService.deleteRequest(id);
    }
    
    @PostMapping("/{requestId}/assign/employee/{employeeId}")
    public ResponseEntity<String> assignEmployee(@PathVariable Long requestId, @PathVariable Long employeeId) {
        return requestService.assignEmployee(requestId, employeeId);
    }
    
    @PostMapping("/{requestId}/assign/item/{itemId}")
    public ResponseEntity<String> assignItem(@PathVariable Long requestId, @PathVariable Long itemId) {
        return requestService.assignItem(requestId, itemId);
    }
}

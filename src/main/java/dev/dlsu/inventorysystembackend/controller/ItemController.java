package dev.dlsu.inventorysystembackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import dev.dlsu.inventorysystembackend.model.Item;
import dev.dlsu.inventorysystembackend.service.ItemService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/item")
public class ItemController {
    
    private final ItemService itemService;
    
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    
    @GetMapping
    public List<Item> findAll() {
        return itemService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<String> saveItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }
    
    @GetMapping("/{id}")
    public Item findItemById(@PathVariable Long id){
        return itemService.findItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody Item item, @PathVariable Long id) {
        return itemService.editItem(item, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
    
    @PostMapping("/{itemId}/assign/employee/{employeeId}")
    public ResponseEntity<String> assignEmpoyee(@PathVariable Long itemId, @PathVariable Long employeeId) {
        return itemService.assignEmployee(itemId, employeeId);
    }
    
    @PostMapping("/{itemId}/assign/location/{locationId}")
    public ResponseEntity<String> assignLocation(@PathVariable Long itemId, @PathVariable Long locationId) {
        return itemService.assignLocation(itemId, locationId);
    }
}

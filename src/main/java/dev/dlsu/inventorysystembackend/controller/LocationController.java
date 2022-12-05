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

import dev.dlsu.inventorysystembackend.model.Location;
import dev.dlsu.inventorysystembackend.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    
    @GetMapping
    public List<Location> findAll() {
        return locationService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Location location) {
        return locationService.saveEmployee(location);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Location location, @PathVariable Long id) {
        return locationService.editEmployee(location, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return locationService.deleteEmployee(id);
    }
    
}

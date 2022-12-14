package dev.dlsu.inventorysystembackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Item;
import dev.dlsu.inventorysystembackend.model.Location;
import dev.dlsu.inventorysystembackend.repository.ItemRepository;
import dev.dlsu.inventorysystembackend.repository.LocationRepository;

@Service
public class LocationService {
    
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;
    
    @Autowired
    public LocationService(LocationRepository locationRepository, ItemRepository itemRepository) {
        this.locationRepository = locationRepository;
        this.itemRepository = itemRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public ResponseEntity<String> saveLocation(Location location) {
        locationRepository.save(location);
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    public ResponseEntity<String> editLocation(Location location, Long id) {
        Optional<Location> target = locationRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Item found", HttpStatus.NOT_FOUND);
        }
        
        Location locationEntity = target.get();
        
        locationEntity.setAddress(location.getAddress());
        locationEntity.setCity(location.getCity());
        locationEntity.setCountry(location.getCountry());
        
        locationRepository.save(locationEntity);
        
        return new ResponseEntity<String>("Update success", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteLocation(Long id) {
        Optional<Location> target = locationRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Item found", HttpStatus.NOT_FOUND);
        }
        
        ArrayList<Item> relatedItems = itemRepository.findAllByLocation(target.get());
        
        relatedItems.stream()
                    .forEach(item -> item.setLocation(null));
        
        itemRepository.saveAll(relatedItems);
        
        locationRepository.delete(target.get());
        
        return new ResponseEntity<String>("Delete success", HttpStatus.OK);
    }

    public Location getLocationById(Long id) {
        Optional<Location> target = locationRepository.findById(id);
        
        if (target.isEmpty()) {
            return null;
        }
        
        return target.get();
    }
    
}

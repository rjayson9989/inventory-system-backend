package dev.dlsu.inventorysystembackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.model.Item;
import dev.dlsu.inventorysystembackend.model.Location;
import dev.dlsu.inventorysystembackend.repository.EmployeeRepository;
import dev.dlsu.inventorysystembackend.repository.ItemRepository;
import dev.dlsu.inventorysystembackend.repository.LocationRepository;

@Service
public class ItemService {

private final EmployeeRepository employeeRepository; 
private final ItemRepository itemRepository;
private final LocationRepository locationRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository, EmployeeRepository employeeRepository, LocationRepository locationRepository) {
        this.itemRepository = itemRepository;
        this.employeeRepository = employeeRepository;
        this.locationRepository = locationRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public ResponseEntity<String> saveItem(Item item, Long id, Long locationId) {
        Optional<Employee> target=employeeRepository.findById(id);

        if (target.isEmpty()) {
            return new ResponseEntity<String>("Employee does not exist", HttpStatus.NOT_FOUND);
        }

        Optional<Location> targetLocation = locationRepository.findById(locationId);

        if (targetLocation.isEmpty()) {
            return new ResponseEntity<String>("Location does not exist", HttpStatus.NOT_FOUND);
        }

        Location locationEntity = targetLocation.get();
        Employee employeeEntity = target.get();
        item.setEmployee(employeeEntity);
        item.setLocation(locationEntity);
        itemRepository.save(item);
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    public ResponseEntity<String> editItem(Item item, Long id) {
        Optional<Item> target = itemRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }
        
        Item itemEntity = target.get();
        
        itemEntity.setType(item.getType());
        itemEntity.setName(item.getName());
        itemEntity.setPurchaseDate(item.getPurchaseDate());
        
        itemRepository.save(itemEntity);
        
        return new ResponseEntity<String>("Update success", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteItem(Long id) {
        Optional<Item> target = itemRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }
        
        itemRepository.delete(target.get());
        
        return new ResponseEntity<String>("Delete success", HttpStatus.OK);
    }
    
}

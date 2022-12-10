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

    public ResponseEntity<String> saveItem(Item item) {
        itemRepository.save(item);
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    public ResponseEntity<String> editItem(Item item, Long id) {
        Optional<Item> target = itemRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Item found", HttpStatus.NOT_FOUND);
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

    public ResponseEntity<String> assignEmployee(Long itemId, Long employeeId) {
        Optional<Item> targetItem = itemRepository.findById(itemId);
        
        if (targetItem.isEmpty()) {
            return new ResponseEntity<String>("No Item found", HttpStatus.NOT_FOUND);
        }
        
        Optional<Employee> targetEmployee = employeeRepository.findById(employeeId);
        
        if (targetEmployee.isEmpty()) {
            return new ResponseEntity<String>("No Employee found", HttpStatus.NOT_FOUND);
        }
        
        Item item = targetItem.get();
        Employee employee = targetEmployee.get();
        
        item.setEmployee(employee);
        
        itemRepository.save(item);
        
        String successString = "Successfully assigned " + item.getName() + " to " + employee.getFirstName() + " " + employee.getLastName();
        
        return new ResponseEntity<String>(successString, HttpStatus.OK);
    }
    
    public ResponseEntity<String> assignLocation(Long itemId, Long locationId) {
        Optional<Item> targetItem = itemRepository.findById(itemId);
        
        if (targetItem.isEmpty()) {
            return new ResponseEntity<String>("No Item found", HttpStatus.NOT_FOUND);
        }
        
        Optional<Location> targetLocation = locationRepository.findById(locationId);
        
        if (targetLocation.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }
        
        Item item = targetItem.get();
        Location location = targetLocation.get();
        
        item.setLocation(location);
        
        itemRepository.save(item);
        
        String successString = "Successfully assigned " + item.getName() + " to " + location.getAddress() + ", " + location.getCity() + ", " + location.getCountry();
        
        return new ResponseEntity<String>(successString, HttpStatus.OK);
    }
    
}

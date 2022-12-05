package dev.dlsu.inventorysystembackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Item;
import dev.dlsu.inventorysystembackend.repository.ItemRepository;

@Service
public class ItemService {
    
private final ItemRepository itemRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

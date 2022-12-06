package dev.dlsu.inventorysystembackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.model.Item;
import dev.dlsu.inventorysystembackend.model.Request;
import dev.dlsu.inventorysystembackend.repository.EmployeeRepository;
import dev.dlsu.inventorysystembackend.repository.ItemRepository;
import dev.dlsu.inventorysystembackend.repository.RequestRepository;

@Service
public class RequestService {
    
    private final RequestRepository requestRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;

    public RequestService(RequestRepository requestRepository,EmployeeRepository employeeRepository,ItemRepository itemRepository) {
        this.requestRepository = requestRepository;
        this.employeeRepository=employeeRepository;
        this.itemRepository=itemRepository;
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public ResponseEntity<String> makeRequest(Request request,Long item_id,Long id) {
        Optional<Employee> target=employeeRepository.findById(id);

        if (target.isEmpty()) {
            return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
        }

        Employee employee_entity=target.get();
        Optional<Item> targetitem=itemRepository.findById(item_id);

        if (targetitem.isEmpty()) {
            return new ResponseEntity<String>("item does not exist", HttpStatus.NOT_FOUND);
        }

        Item item_entity=targetitem.get();
        request.setItem(item_entity);
        request.setEmployee(employee_entity);
        requestRepository.save(request);
        return new ResponseEntity<String>("Request created Successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> saveRequest(Request request) {
        requestRepository.save(request);
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    public ResponseEntity<String> editRequest(Request request, Long id) {
        Optional<Request> target = requestRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }
        
        Request requestEntity = target.get();
        
        requestEntity.setDetails(request.getDetails());
        requestEntity.setStatus(request.getStatus());
        requestEntity.setRequestDate(request.getRequestDate());
        
        requestRepository.save(requestEntity);
        
        return new ResponseEntity<String>("Update success", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteRequest(Long id) {
        Optional<Request> target = requestRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }
        
        requestRepository.delete(target.get());
        
        return new ResponseEntity<String>("Delete success", HttpStatus.OK);
    }
    
    
}

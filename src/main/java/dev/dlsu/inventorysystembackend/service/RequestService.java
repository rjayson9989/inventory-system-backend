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

    public ResponseEntity<String> makeRequest(Request request) {
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

    public ResponseEntity<String> assignEmployee(Long requestId, Long employeeId) {
        Optional<Request> targetRequest = requestRepository.findById(requestId);
        
        if (targetRequest.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }
        
        Optional<Employee> targetEmployee = employeeRepository.findById(employeeId);

        if (targetEmployee.isEmpty()) {
            return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
        }
        
        Request request = targetRequest.get();
        Employee employee = targetEmployee.get();
        
        request.setEmployee(employee);
        
        requestRepository.save(request);
        
        String successString = "Successfully assigned request #" + request.getId() + " to " + employee.getFirstName() + " " + employee.getLastName();
        
        return new ResponseEntity<String>(successString, HttpStatus.OK);
    }

    public ResponseEntity<String> assignItem(Long requestId, Long itemId) {
        Optional<Request> targetRequest = requestRepository.findById(requestId);
        
        if (targetRequest.isEmpty()) {
            return new ResponseEntity<String>("No Location found", HttpStatus.NOT_FOUND);
        }

        Optional<Item> targetItem = itemRepository.findById(itemId);

        if (targetItem.isEmpty()) {
            return new ResponseEntity<String>("item does not exist", HttpStatus.NOT_FOUND);
        }
        
        Request request = targetRequest.get();
        Item item = targetItem.get();
        
        request.setItem(item);
        
        requestRepository.save(request);
        
        String successString = "Successfully assigned request #" + request.getId() + " to " + item.getName();
        
        return new ResponseEntity<String>(successString, HttpStatus.OK);
    }
    
    
}

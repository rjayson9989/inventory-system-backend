package dev.dlsu.inventorysystembackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Request;
import dev.dlsu.inventorysystembackend.repository.RequestRepository;

@Service
public class RequestService {
    
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
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

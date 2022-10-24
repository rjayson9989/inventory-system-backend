package dev.dlsu.inventorysystembackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<String> saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    public ResponseEntity<String> editEmployee(Employee employee, Long id) {
        Optional<Employee> target = employeeRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Employee found", HttpStatus.NOT_FOUND);
        }
        
        Employee employeeEntity = target.get();
        
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setAge(employee.getAge());
        
        employeeRepository.save(employeeEntity);
        
        return new ResponseEntity<String>("Update success", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEmployee(Long id) {
        Optional<Employee> target = employeeRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Employee found", HttpStatus.NOT_FOUND);
        }
        
        employeeRepository.delete(target.get());
        
        return new ResponseEntity<String>("Delete success", HttpStatus.OK);
    }
    
}

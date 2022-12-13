package dev.dlsu.inventorysystembackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<String> saveEmployee(Employee employee) {
        Optional<Employee> findUsername = employeeRepository.findByUsername(employee.getUsername());
        
        if (findUsername.isPresent()) {
            return new ResponseEntity<String>("Username " + employee.getUsername() + " already taken", HttpStatus.CONFLICT);
        }
        
        Optional<Employee> findEmail = employeeRepository.findByEmail(employee.getEmail());
        
        if (findEmail.isPresent()) {
            return new ResponseEntity<String>("Email " + employee.getEmail() + " already taken", HttpStatus.CONFLICT);
        }
        
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return new ResponseEntity<String>("Save success", HttpStatus.OK);
    }

    public ResponseEntity<String> editEmployee(Employee employee, Long id) {
        Optional<Employee> target = employeeRepository.findById(id);
        
        if (target.isEmpty()) {
            return new ResponseEntity<String>("No Employee found", HttpStatus.NOT_FOUND);
        }
        
        Employee employeeEntity = target.get();
        
        Optional<Employee> findUsername = employeeRepository.findByUsername(employee.getUsername());
        
        if (findUsername.isPresent() && findUsername.get().getId() != employeeEntity.getId()) {
            return new ResponseEntity<String>("Username " + employee.getUsername() + " already taken", HttpStatus.CONFLICT);
        }
        
        Optional<Employee> findEmail = employeeRepository.findByEmail(employee.getEmail());
        
        if (findEmail.isPresent() && findEmail.get().getId() != employeeEntity.getId()) {
            return new ResponseEntity<String>("Email " + employee.getEmail() + " already taken", HttpStatus.CONFLICT);
        }
        
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setUsername(employee.getUsername());
        employeeEntity.setPassword(encoder.encode(employee.getPassword()));
        employeeEntity.setRole(employee.getRole());
        
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

    public Employee getEmployeeById(Long id) {
        Optional<Employee> target = employeeRepository.findById(id);
        
        if (target.isEmpty()) {
            return null;
        }
        
        return target.get();
    }
    
}

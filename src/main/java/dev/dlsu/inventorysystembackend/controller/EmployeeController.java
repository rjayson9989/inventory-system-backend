package dev.dlsu.inventorysystembackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    
    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeebyId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.editEmployee(employee, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
    
}

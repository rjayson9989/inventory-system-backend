package dev.dlsu.inventorysystembackend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.dlsu.inventorysystembackend.model.EmployeeUserDetails;
import dev.dlsu.inventorysystembackend.repository.EmployeeRepository;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username)
                                 .map(EmployeeUserDetails::new)
                                 .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

}

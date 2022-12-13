package dev.dlsu.inventorysystembackend.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeUserDetails implements UserDetails {
    
    private final Employee employee;
    
    public EmployeeUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }
    
    @Override
    public String getPassword() {
        return employee.getPassword();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(employee.getRole().split(","))
                     .map(SimpleGrantedAuthority::new)
                     .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}

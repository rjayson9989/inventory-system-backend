package dev.dlsu.inventorysystembackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Employee")
@Table(
        name = "employee",
        uniqueConstraints = {
                    @UniqueConstraint(name = "employee_email_unique", columnNames = "email"),
                    @UniqueConstraint(name = "employee_username_unique", columnNames = "username")
            }
        )
public class Employee {
    
    @Id
    @SequenceGenerator(
                name = "employee_sequence",
                sequenceName = "employee_sequence",
                allocationSize = 1
            )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "employee_sequence"
            )
    @Column(
                name = "id",
                updatable = false
            )
    private Long id;
    
    @Column(
                name = "first_name",
                nullable = false
            )
    private String firstName;
    
    @Column(
                name = "last_name",
                nullable = false
            )
    private String lastName;
    
    @Column(
                name = "email",
                nullable = false
            )
    private String email;
    
    @Column(
                name = "username",
                nullable = false
            )
    private String username;
    
    @Column(
            name = "password",
            nullable = false
        )
    private String password;
    
    @Column(
            name = "role",
            nullable = false
        )
    private String role;
    
    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, String username, String password,
            String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}

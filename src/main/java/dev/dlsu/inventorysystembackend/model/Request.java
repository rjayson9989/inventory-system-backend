package dev.dlsu.inventorysystembackend.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "request")
@Table(name = "request")
public class Request {
    
    @Id
    @SequenceGenerator(
                name = "request_sequence",
                sequenceName = "request_sequence",
                allocationSize = 1
            )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "request_sequence"
            )
    @Column(
                name = "id",
                updatable = false
            )
    private Long id;
    
    @Column(
            name = "details",
            nullable = false,
            columnDefinition = "TEXT"
        )
    private String details;
    
    @Column(
            name = "status",
            nullable = false
        )
    private String status;
    
    @Column(
            name = "date_issued",
            nullable = false
        )
    private LocalDate requestDate;
    
    public Request() {
    }
        
    public Request(Long id, String details, String status, LocalDate requestDate) {
        this.id = id;
        this.details = details;
        this.status = status;
        this.requestDate = requestDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    @JoinColumn(
        name="employee_id",
        referencedColumnName = "id"
    )
    private Employee employee;

    public void setEmployee(Employee employee)
    {
        this.employee=employee;
    }
    
    @OneToOne(
         cascade = CascadeType.ALL
    )
    @JoinColumn(
        name="Item_id",
        referencedColumnName = "id"
    )
    private Item item; 
    
    public void setItem(Item item)
    {
        this.item=item;
    }
}

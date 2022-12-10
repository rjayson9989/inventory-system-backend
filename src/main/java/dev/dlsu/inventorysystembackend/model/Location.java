package dev.dlsu.inventorysystembackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "location")
@Table(name = "location")
public class Location {
    
    @Id
    @SequenceGenerator(
                name = "location_sequence",
                sequenceName = "location_sequence",
                allocationSize = 1
            )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "location_sequence"
            )
    @Column(
                name = "id",
                updatable = false
            )
    private Long id;
    
    @Column(
            name = "address",
            nullable = false
        )
    private String address;
    
    @Column(
            name = "city",
            nullable = false
        )
    private String city;
    
    @Column(
            name = "country",
            nullable = false
        )
    private String country;

    public Location() {
    }

    public Location(Long id, String address, String city, String country) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    
}

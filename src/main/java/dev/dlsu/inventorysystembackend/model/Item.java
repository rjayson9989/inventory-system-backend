package dev.dlsu.inventorysystembackend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Item")
@Table(name = "item")
public class Item {
    
    @Id
    @SequenceGenerator(
                name = "item_sequence",
                sequenceName = "item_sequence",
                allocationSize = 1
            )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "item_sequence"
            )
    @Column(
                name = "id",
                updatable = false
            )
    private Long id;
    
    @Column(
            name = "type",
            nullable = false
        )
    private String type;
    
    @Column(
            name = "name",
            nullable = false
        )
    private String name;
    
    @Column(
            name = "dop",
            nullable = false
        )
    private LocalDate purchaseDate;

    public Item() {
    }

    public Item(Long id, String type, String name, LocalDate purchaseDate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
}

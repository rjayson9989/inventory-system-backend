package dev.dlsu.inventorysystembackend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.model.Item;
import dev.dlsu.inventorysystembackend.model.Location;

public interface ItemRepository extends JpaRepository<Item, Long> {

    ArrayList<Item> findAllByEmployee(Employee employee);

    ArrayList<Item> findAllByLocation(Location location);

}

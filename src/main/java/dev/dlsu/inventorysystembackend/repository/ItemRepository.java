package dev.dlsu.inventorysystembackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dlsu.inventorysystembackend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

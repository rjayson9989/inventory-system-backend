package dev.dlsu.inventorysystembackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dlsu.inventorysystembackend.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}

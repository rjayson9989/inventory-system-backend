package dev.dlsu.inventorysystembackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.dlsu.inventorysystembackend.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}

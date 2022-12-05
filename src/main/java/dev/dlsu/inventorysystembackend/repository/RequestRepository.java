package dev.dlsu.inventorysystembackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.dlsu.inventorysystembackend.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

}

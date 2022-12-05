package dev.dlsu.inventorysystembackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dlsu.inventorysystembackend.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}

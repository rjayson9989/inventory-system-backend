package dev.dlsu.inventorysystembackend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.dlsu.inventorysystembackend.model.Employee;
import dev.dlsu.inventorysystembackend.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    ArrayList<Request> findAllByEmployee(Employee employee);

}

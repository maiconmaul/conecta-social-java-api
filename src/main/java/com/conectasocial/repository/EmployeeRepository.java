package com.conectasocial.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.conectasocial.domain.entity.Employee;
import com.conectasocial.domain.enums.EmployeeRole;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    
    Optional<Employee> findByEmail(String email);
    
    Optional<Employee> findByCpf(String cpf);
    
    List<Employee> findByActiveTrue();
    
    List<Employee> findByRole(EmployeeRole role);
    
    @Query("SELECT e FROM Employee e WHERE e.active = true AND (LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(e.surname) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Employee> findActiveEmployeesByNameOrSurname(@Param("search") String search);
    
    boolean existsByEmail(String email);
    
    boolean existsByCpf(String cpf);
}

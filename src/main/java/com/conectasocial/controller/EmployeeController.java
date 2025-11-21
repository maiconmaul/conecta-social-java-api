package com.conectasocial.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conectasocial.dto.employee.CreateEmployeeRequest;
import com.conectasocial.dto.employee.EmployeeResponse;
import com.conectasocial.dto.employee.UpdateEmployeeRequest;
import com.conectasocial.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employees", description = "Endpoints para gestão de funcionários")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping
    @Operation(summary = "Criar funcionário", description = "Cria um novo funcionário")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar funcionários", description = "Lista todos os funcionários ativos")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID", description = "Busca um funcionário específico por ID")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable UUID id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário", description = "Atualiza os dados de um funcionário")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable UUID id, 
                                                          @Valid @RequestBody UpdateEmployeeRequest request) {
        EmployeeResponse response = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar funcionário", description = "Desativa um funcionário (soft delete)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> disableEmployee(@PathVariable UUID id) {
        employeeService.disableEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar funcionários", description = "Busca funcionários por nome ou sobrenome")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(@RequestParam String search) {
        List<EmployeeResponse> employees = employeeService.searchEmployees(search);
        return ResponseEntity.ok(employees);
    }
}

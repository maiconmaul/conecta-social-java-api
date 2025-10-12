package com.conectasocial.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conectasocial.domain.entity.Employee;
import com.conectasocial.dto.employee.CreateEmployeeRequest;
import com.conectasocial.dto.employee.EmployeeResponse;
import com.conectasocial.dto.employee.UpdateEmployeeRequest;
import com.conectasocial.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        // Verificar se email já existe
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }
        
        // Verificar se CPF já existe
        if (employeeRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("CPF já está em uso");
        }
        
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setBirthDate(request.getBirthDate());
        employee.setCpf(request.getCpf());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(request.getRole());
        employee.setCep(request.getCep());
        employee.setStreet(request.getStreet());
        employee.setNeighborhood(request.getNeighborhood());
        employee.setNumber(request.getNumber());
        employee.setCity(request.getCity());
        employee.setUf(request.getUf());
        employee.setState(request.getState());
        employee.setComplement(request.getComplement());
        employee.setActive(true);
        
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeResponse(savedEmployee);
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findByActiveTrue()
                .stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        if (!employee.getActive()) {
            throw new RuntimeException("Funcionário inativo");
        }
        
        return new EmployeeResponse(employee);
    }
    
    public EmployeeResponse updateEmployee(UUID id, UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        if (!employee.getActive()) {
            throw new RuntimeException("Funcionário inativo");
        }
        
        employee.setName(request.getName());
        employee.setSurname(request.getSurname());
        employee.setBirthDate(request.getBirthDate());
        employee.setPhone(request.getPhone());
        employee.setRole(request.getRole());
        employee.setCep(request.getCep());
        employee.setStreet(request.getStreet());
        employee.setNeighborhood(request.getNeighborhood());
        employee.setNumber(request.getNumber());
        employee.setCity(request.getCity());
        employee.setUf(request.getUf());
        employee.setState(request.getState());
        employee.setComplement(request.getComplement());
        
        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeResponse(updatedEmployee);
    }
    
    public void disableEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        employee.setActive(false);
        employeeRepository.save(employee);
    }
    
    @Transactional(readOnly = true)
    public List<EmployeeResponse> searchEmployees(String search) {
        return employeeRepository.findActiveEmployeesByNameOrSurname(search)
                .stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }
}

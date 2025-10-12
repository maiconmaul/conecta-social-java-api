package com.conectasocial.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.conectasocial.domain.enums.EmployeeRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    
    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(max = 60, message = "Sobrenome deve ter no máximo 60 caracteres")
    @Column(name = "surname", length = 60, nullable = false)
    private String surname;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 dígitos")
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    @Size(max = 60, message = "Email deve ter no máximo 60 caracteres")
    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    @Column(name = "phone", length = 15, nullable = false)
    private String phone;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(max = 128, message = "Senha deve ter no máximo 128 caracteres")
    @Column(name = "password", length = 128, nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EmployeeRole role = EmployeeRole.VOLUNTEER;
    
    // Endereço
    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    
    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua deve ter no máximo 100 caracteres")
    @Column(name = "street", length = 100, nullable = false)
    private String street;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 60, message = "Bairro deve ter no máximo 60 caracteres")
    @Column(name = "neighborhood", length = 60, nullable = false)
    private String neighborhood;
    
    @NotBlank(message = "Número é obrigatório")
    @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
    @Column(name = "number", length = 20, nullable = false)
    private String number;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 30, message = "Cidade deve ter no máximo 30 caracteres")
    @Column(name = "city", length = 30, nullable = false)
    private String city;
    
    @NotBlank(message = "UF é obrigatória")
    @Size(max = 2, message = "UF deve ter exatamente 2 caracteres")
    @Column(name = "uf", length = 2, nullable = false)
    private String uf;
    
    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 20, message = "Estado deve ter no máximo 20 caracteres")
    @Column(name = "state", length = 20, nullable = false)
    private String state;
    
    @Size(max = 30, message = "Complemento deve ter no máximo 30 caracteres")
    @Column(name = "complement", length = 30)
    private String complement;
    
    @Column(name = "active", nullable = false)
    private Boolean active = true;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LogEmployeeEvent> logs;
    
    // Constructors
    public Employee() {}
    
    public Employee(String name, String surname, LocalDate birthDate, String cpf, 
                   String email, String phone, String password, EmployeeRole role,
                   String cep, String street, String neighborhood, String number,
                   String city, String uf, String state, String complement) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.cep = cep;
        this.street = street;
        this.neighborhood = neighborhood;
        this.number = number;
        this.city = city;
        this.uf = uf;
        this.state = state;
        this.complement = complement;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public EmployeeRole getRole() {
        return role;
    }
    
    public void setRole(EmployeeRole role) {
        this.role = role;
    }
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getNeighborhood() {
        return neighborhood;
    }
    
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getUf() {
        return uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getComplement() {
        return complement;
    }
    
    public void setComplement(String complement) {
        this.complement = complement;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<LogEmployeeEvent> getLogs() {
        return logs;
    }
    
    public void setLogs(List<LogEmployeeEvent> logs) {
        this.logs = logs;
    }
}

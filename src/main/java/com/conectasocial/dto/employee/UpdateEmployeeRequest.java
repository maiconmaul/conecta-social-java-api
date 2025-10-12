package com.conectasocial.dto.employee;

import java.time.LocalDate;

import com.conectasocial.domain.enums.EmployeeRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
    private String name;
    
    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(max = 60, message = "Sobrenome deve ter no máximo 60 caracteres")
    private String surname;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate birthDate;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    private String phone;
    
    private EmployeeRole role;
    
    // Endereço
    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
    private String cep;
    
    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua deve ter no máximo 100 caracteres")
    private String street;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 60, message = "Bairro deve ter no máximo 60 caracteres")
    private String neighborhood;
    
    @NotBlank(message = "Número é obrigatório")
    @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
    private String number;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 30, message = "Cidade deve ter no máximo 30 caracteres")
    private String city;
    
    @NotBlank(message = "UF é obrigatória")
    @Size(max = 2, message = "UF deve ter exatamente 2 caracteres")
    private String uf;
    
    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 20, message = "Estado deve ter no máximo 20 caracteres")
    private String state;
    
    @Size(max = 30, message = "Complemento deve ter no máximo 30 caracteres")
    private String complement;
    
    // Constructors
    public UpdateEmployeeRequest() {}
    
    // Getters and Setters
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
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
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
}

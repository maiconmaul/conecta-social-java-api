package com.conectasocial.dto.employee;

import java.time.LocalDate;

import com.conectasocial.domain.enums.EmployeeRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEmployeeRequest {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 30, message = "Nome deve ter no máximo 30 caracteres")
    private String name;
    
    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(max = 60, message = "Sobrenome deve ter no máximo 60 caracteres")
    private String surname;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate birthDate;
    
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 dígitos")
    private String cpf;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    @Size(max = 60, message = "Email deve ter no máximo 60 caracteres")
    private String email;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    private String phone;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;
    
    private EmployeeRole role = EmployeeRole.VOLUNTEER;
    
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
    public CreateEmployeeRequest() {}
    
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
}

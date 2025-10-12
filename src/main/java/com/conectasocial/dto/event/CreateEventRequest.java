package com.conectasocial.dto.event;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateEventRequest {
    
    @NotBlank(message = "Nome do evento é obrigatório")
    @Size(max = 100, message = "Nome do evento deve ter no máximo 100 caracteres")
    private String name;
    
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String description;
    
    @NotNull(message = "Data do evento é obrigatória")
    private LocalDateTime date;
    
    @Size(max = 1000, message = "Descrição da saudação deve ter no máximo 1000 caracteres")
    private String greetingDescription;
    
    @Min(value = 0, message = "Participação deve ser um número positivo")
    private Integer attendance;
    
    private String embeddedInstagram;
    
    @NotBlank(message = "Status é obrigatório")
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    private String status;
    
    // Endereço
    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua deve ter no máximo 100 caracteres")
    private String street;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 30, message = "Bairro deve ter no máximo 30 caracteres")
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
    
    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
    private String cep;
    
    @Size(max = 30, message = "Complemento deve ter no máximo 30 caracteres")
    private String complement;
    
    // Constructors
    public CreateEventRequest() {}
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public String getGreetingDescription() {
        return greetingDescription;
    }
    
    public void setGreetingDescription(String greetingDescription) {
        this.greetingDescription = greetingDescription;
    }
    
    public Integer getAttendance() {
        return attendance;
    }
    
    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
    
    public String getEmbeddedInstagram() {
        return embeddedInstagram;
    }
    
    public void setEmbeddedInstagram(String embeddedInstagram) {
        this.embeddedInstagram = embeddedInstagram;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getComplement() {
        return complement;
    }
    
    public void setComplement(String complement) {
        this.complement = complement;
    }
}

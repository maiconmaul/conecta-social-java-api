package com.conectasocial.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank(message = "Nome do evento é obrigatório")
    @Size(max = 100, message = "Nome do evento deve ter no máximo 100 caracteres")
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Column(name = "description", length = 1000)
    private String description;
    
    @NotNull(message = "Data do evento é obrigatória")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    
    @Size(max = 1000, message = "Descrição da saudação deve ter no máximo 1000 caracteres")
    @Column(name = "greeting_description", length = 1000)
    private String greetingDescription;
    
    @Min(value = 0, message = "Participação deve ser um número positivo")
    @Column(name = "attendance")
    private Integer attendance;
    
    @Column(name = "embedded_instagram", columnDefinition = "TEXT")
    private String embeddedInstagram;
    
    @NotBlank(message = "Status é obrigatório")
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    @Column(name = "status", length = 20, nullable = false)
    private String status;
    
    // Endereço
    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua deve ter no máximo 100 caracteres")
    @Column(name = "street", length = 100, nullable = false)
    private String street;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 30, message = "Bairro deve ter no máximo 30 caracteres")
    @Column(name = "neighborhood", length = 30, nullable = false)
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
    
    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    
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
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LogEmployeeEvent> logs;
    
    // Constructors
    public Event() {}
    
    public Event(String name, String description, LocalDateTime date, String greetingDescription,
                Integer attendance, String embeddedInstagram, String status,
                String street, String neighborhood, String number, String city,
                String uf, String state, String cep, String complement) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.greetingDescription = greetingDescription;
        this.attendance = attendance;
        this.embeddedInstagram = embeddedInstagram;
        this.status = status;
        this.street = street;
        this.neighborhood = neighborhood;
        this.number = number;
        this.city = city;
        this.uf = uf;
        this.state = state;
        this.cep = cep;
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

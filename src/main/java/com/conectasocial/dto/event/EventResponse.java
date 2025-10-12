package com.conectasocial.dto.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.conectasocial.domain.entity.Event;

public class EventResponse {
    
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime date;
    private String greetingDescription;
    private Integer attendance;
    private String embeddedInstagram;
    private String status;
    private String street;
    private String neighborhood;
    private String number;
    private String city;
    private String uf;
    private String state;
    private String cep;
    private String complement;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public EventResponse() {}
    
    public EventResponse(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.greetingDescription = event.getGreetingDescription();
        this.attendance = event.getAttendance();
        this.embeddedInstagram = event.getEmbeddedInstagram();
        this.status = event.getStatus();
        this.street = event.getStreet();
        this.neighborhood = event.getNeighborhood();
        this.number = event.getNumber();
        this.city = event.getCity();
        this.uf = event.getUf();
        this.state = event.getState();
        this.cep = event.getCep();
        this.complement = event.getComplement();
        this.active = event.getActive();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
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
}

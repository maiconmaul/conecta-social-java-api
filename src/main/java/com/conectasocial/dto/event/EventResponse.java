package com.conectasocial.dto.event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.conectasocial.domain.entity.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResponse {
    
    private String id;
    private String name;
    private String description;
    private LocalDateTime date;
    
    @JsonProperty("greeting_description")
    private String greetingDescription;
    
    private Integer attendance;
    
    @JsonProperty("embedded_instagram")
    private String embedded_instagram;
    
    private String status;
    private String street;
    private String neighborhood;
    private String number;
    private String city;
    private String state;
    private String cep;
    private String complement;
    private List<LogVolunteerEventResponse> logs;
    
    // Constructors
    public EventResponse() {}
    
    public EventResponse(Event event) {
        this.id = event.getId() != null ? event.getId().toString() : null;
        this.name = event.getName();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.greetingDescription = event.getGreetingDescription();
        this.attendance = event.getAttendance();
        this.embedded_instagram = event.getEmbedded_instagram();
        this.status = event.getStatus();
        this.street = event.getStreet();
        this.neighborhood = event.getNeighborhood();
        this.number = event.getNumber();
        this.city = event.getCity();
        this.state = event.getState();
        this.cep = event.getCep();
        this.complement = event.getComplement();
        
        // Mapeia os logs se existirem
        if (event.getLogs() != null && !event.getLogs().isEmpty()) {
            this.logs = event.getLogs().stream()
                    .map(LogVolunteerEventResponse::new)
                    .collect(Collectors.toList());
        }
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setId(UUID id) {
        this.id = id != null ? id.toString() : null;
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
    
    public String getEmbedded_instagram() {
        return embedded_instagram;
    }
    
    public void setEmbedded_instagram(String embedded_instagram) {
        this.embedded_instagram = embedded_instagram;
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
    
    public List<LogVolunteerEventResponse> getLogs() {
        return logs;
    }
    
    public void setLogs(List<LogVolunteerEventResponse> logs) {
        this.logs = logs;
    }
}

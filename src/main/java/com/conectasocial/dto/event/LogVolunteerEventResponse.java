package com.conectasocial.dto.event;

import java.time.LocalDateTime;

import com.conectasocial.domain.entity.LogEmployeeEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogVolunteerEventResponse {
    
    private String id;
    
    @JsonProperty("event_id")
    private String eventId;
    
    @JsonProperty("volunteer_id")
    private String volunteerId;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public LogVolunteerEventResponse() {}
    
    public LogVolunteerEventResponse(LogEmployeeEvent log) {
        this.id = log.getId() != null ? log.getId().toString() : null;
        this.eventId = log.getIdEvent() != null ? log.getIdEvent().toString() : null;
        this.volunteerId = log.getIdEmployee() != null ? log.getIdEmployee().toString() : null;
        this.createdAt = log.getCreatedAt();
        // LogEmployeeEvent não tem updated_at, então usamos created_at ou null
        this.updatedAt = log.getCreatedAt();
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public String getVolunteerId() {
        return volunteerId;
    }
    
    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
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


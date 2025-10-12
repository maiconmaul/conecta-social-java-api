package com.conectasocial.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "log_employee_event")
public class LogEmployeeEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotNull(message = "ID do funcionário é obrigatório")
    @Column(name = "id_employee", length = 25, nullable = false)
    private UUID idEmployee;
    
    @NotNull(message = "ID do evento é obrigatório")
    @Column(name = "id_event", length = 25, nullable = false)
    private UUID idEvent;
    
    @NotBlank(message = "Mensagem do log é obrigatória")
    @Size(max = 100, message = "Mensagem do log deve ter no máximo 100 caracteres")
    @Column(name = "log_message", length = 100, nullable = false)
    private String logMessage;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event", referencedColumnName = "id", insertable = false, updatable = false)
    private Event event;
    
    // Constructors
    public LogEmployeeEvent() {}
    
    public LogEmployeeEvent(UUID idEmployee, UUID idEvent, String logMessage) {
        this.idEmployee = idEmployee;
        this.idEvent = idEvent;
        this.logMessage = logMessage;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getIdEmployee() {
        return idEmployee;
    }
    
    public void setIdEmployee(UUID idEmployee) {
        this.idEmployee = idEmployee;
    }
    
    public UUID getIdEvent() {
        return idEvent;
    }
    
    public void setIdEvent(UUID idEvent) {
        this.idEvent = idEvent;
    }
    
    public String getLogMessage() {
        return logMessage;
    }
    
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
}

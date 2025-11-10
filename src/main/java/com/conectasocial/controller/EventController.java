package com.conectasocial.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conectasocial.dto.ApiResponse;
import com.conectasocial.dto.event.CreateEventRequest;
import com.conectasocial.dto.event.EventResponse;
import com.conectasocial.dto.event.UpdateEventRequest;
import com.conectasocial.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Endpoints para gestão de eventos")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @PostMapping
    @Operation(summary = "Criar evento", description = "Cria um novo evento")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<EventResponse>> createEvent(@Valid @RequestBody CreateEventRequest request) {
        try {
            EventResponse eventData = eventService.createEvent(request);
            ApiResponse<EventResponse> response = ApiResponse.success(eventData);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<EventResponse> errorResponse = ApiResponse.error(400, "Erro ao criar evento: " + e.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
    
    @GetMapping
    @Operation(summary = "Listar eventos", description = "Lista todos os eventos ativos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        List<EventResponse> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/actives")
    @Operation(summary = "Listar eventos ativos", description = "Lista todos os eventos ativos")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<ApiResponse<List<EventResponse>>> getActiveEvents() {
        try {
            List<EventResponse> events = eventService.getAllEvents();
            ApiResponse<List<EventResponse>> response = ApiResponse.success(events);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<EventResponse>> errorResponse = ApiResponse.error(500, "Erro ao listar eventos: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("/recent-with-instagram")
    @Operation(summary = "Listar eventos recentes com Instagram", description = "Lista eventos ativos que possuem embed do Instagram")
    public ResponseEntity<ApiResponse<List<EventResponse>>> getEventsWithInstagram(
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        try {
            List<EventResponse> events;
            if (limit > 0) {
                events = eventService.getEventsWithInstagramLimit(limit);
            } else {
                events = eventService.getEventsWithInstagram();
            }
            ApiResponse<List<EventResponse>> response = ApiResponse.success(events);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<EventResponse>> errorResponse = ApiResponse.error(500, "Erro ao listar eventos com Instagram: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento por ID", description = "Busca um evento específico por ID")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<ApiResponse<EventResponse>> getEventById(@PathVariable UUID id) {
        try {
            EventResponse event = eventService.getEventById(id);
            ApiResponse<EventResponse> response = ApiResponse.success(event);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<EventResponse> errorResponse = ApiResponse.error(404, "Evento não encontrado: " + e.getMessage());
            return ResponseEntity.status(404).body(errorResponse);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar evento", description = "Atualiza os dados de um evento")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<EventResponse>> updateEvent(@PathVariable UUID id, 
                                                    @Valid @RequestBody UpdateEventRequest request) {
        try {
            EventResponse eventData = eventService.updateEvent(id, request);
            ApiResponse<EventResponse> response = ApiResponse.success(eventData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<EventResponse> errorResponse = ApiResponse.error(400, "Erro ao atualizar evento: " + e.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar evento", description = "Desativa um evento (soft delete)")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> disableEvent(@PathVariable UUID id) {
        eventService.disableEvent(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar eventos", description = "Busca eventos por nome")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EventResponse>> searchEvents(@RequestParam String search) {
        List<EventResponse> events = eventService.searchEvents(search);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/upcoming")
    @Operation(summary = "Listar eventos futuros", description = "Lista eventos que ainda não aconteceram")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EventResponse>> getUpcomingEvents() {
        List<EventResponse> events = eventService.getUpcomingEvents();
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/past")
    @Operation(summary = "Listar eventos passados", description = "Lista eventos que já aconteceram")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EventResponse>> getPastEvents() {
        List<EventResponse> events = eventService.getPastEvents();
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Listar eventos por status", description = "Lista eventos filtrados por status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('VOLUNTEER')")
    public ResponseEntity<List<EventResponse>> getEventsByStatus(@PathVariable String status) {
        List<EventResponse> events = eventService.getEventsByStatus(status);
        return ResponseEntity.ok(events);
    }
}

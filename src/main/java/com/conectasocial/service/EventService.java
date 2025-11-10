package com.conectasocial.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conectasocial.domain.entity.Event;
import com.conectasocial.dto.event.CreateEventRequest;
import com.conectasocial.dto.event.EventResponse;
import com.conectasocial.dto.event.UpdateEventRequest;
import com.conectasocial.dto.event.UpdateInstagramEmbedRequest;
import com.conectasocial.repository.EventRepository;

@Service
@Transactional
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private InstagramEmbedService instagramEmbedService;
    
    public EventResponse createEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setGreetingDescription(request.getGreetingDescription());
        event.setAttendance(request.getAttendance());
        
        // Converte URL do Instagram em embed HTML se necessário
        String embedded_instagram = request.getEmbedded_instagram();
        if (embedded_instagram != null && !embedded_instagram.trim().isEmpty()) {
            embedded_instagram = instagramEmbedService.getEmbedFromUrl(embedded_instagram);
        }
        event.setEmbedded_instagram(embedded_instagram);
        
        event.setStatus(request.getStatus());
        event.setStreet(request.getStreet());
        event.setNeighborhood(request.getNeighborhood());
        event.setNumber(request.getNumber());
        event.setCity(request.getCity());
        event.setState(request.getState());
        event.setCep(request.getCep());
        event.setComplement(request.getComplement());
        event.setActive(true);
        
        Event savedEvent = eventRepository.save(event);
        return new EventResponse(savedEvent);
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {
        return eventRepository.findByActiveTrue()
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> getEventsWithInstagram() {
        return eventRepository.findActiveEventsWithInstagram()
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> getEventsWithInstagramLimit(int limit) {
        return eventRepository.findActiveEventsWithInstagram()
                .stream()
                .limit(limit)
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public EventResponse getEventById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        if (!event.getActive()) {
            throw new RuntimeException("Evento inativo");
        }
        
        return new EventResponse(event);
    }
    
    public EventResponse updateEvent(UUID id, UpdateEventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        if (!event.getActive()) {
            throw new RuntimeException("Evento inativo");
        }
        
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setGreetingDescription(request.getGreetingDescription());
        event.setAttendance(request.getAttendance());
        
        // Converte URL do Instagram em embed HTML se necessário
        String embedded_instagram = request.getEmbedded_instagram();
        if (embedded_instagram != null && !embedded_instagram.trim().isEmpty()) {
            embedded_instagram = instagramEmbedService.getEmbedFromUrl(embedded_instagram);
        }
        event.setEmbedded_instagram(embedded_instagram);
        
        event.setStatus(request.getStatus());
        event.setStreet(request.getStreet());
        event.setNeighborhood(request.getNeighborhood());
        event.setNumber(request.getNumber());
        event.setCity(request.getCity());
        event.setState(request.getState());
        event.setCep(request.getCep());
        event.setComplement(request.getComplement());
        
        Event updatedEvent = eventRepository.save(event);
        return new EventResponse(updatedEvent);
    }
    
    public void disableEvent(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        event.setActive(false);
        eventRepository.save(event);
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> searchEvents(String search) {
        return eventRepository.findActiveEventsByName(search)
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now())
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> getPastEvents() {
        return eventRepository.findPastEvents(LocalDateTime.now())
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EventResponse> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status)
                .stream()
                .filter(Event::getActive)
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
    
    public EventResponse updateInstagramEmbed(UUID id, UpdateInstagramEmbedRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        if (!event.getActive()) {
            throw new RuntimeException("Evento inativo");
        }
        
        // Converte URL do Instagram em embed HTML se necessário
        String embedded_instagram = request.getEmbedded_instagram();
        if (embedded_instagram != null && !embedded_instagram.trim().isEmpty()) {
            embedded_instagram = instagramEmbedService.getEmbedFromUrl(embedded_instagram);
        }
        event.setEmbedded_instagram(embedded_instagram);
        
        Event updatedEvent = eventRepository.save(event);
        return new EventResponse(updatedEvent);
    }
}

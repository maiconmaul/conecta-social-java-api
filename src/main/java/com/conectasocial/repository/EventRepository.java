package com.conectasocial.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.conectasocial.domain.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    
    List<Event> findByActiveTrue();
    
    List<Event> findByStatus(String status);
    
    @Query("SELECT e FROM Event e WHERE e.active = true AND e.date >= :startDate AND e.date <= :endDate")
    List<Event> findActiveEventsByDateRange(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT e FROM Event e WHERE e.active = true AND LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Event> findActiveEventsByName(@Param("search") String search);
    
    @Query("SELECT e FROM Event e WHERE e.active = true AND e.date >= :date")
    List<Event> findUpcomingEvents(@Param("date") LocalDateTime date);
    
    @Query("SELECT e FROM Event e WHERE e.active = true AND e.date < :date")
    List<Event> findPastEvents(@Param("date") LocalDateTime date);
    
    @Query("SELECT e FROM Event e WHERE e.active = true AND e.embeddedInstagram IS NOT NULL AND e.embeddedInstagram != '' ORDER BY e.date DESC")
    List<Event> findActiveEventsWithInstagram();
    
    @Query(value = "SELECT * FROM events WHERE active = true AND embedded_instagram IS NOT NULL AND embedded_instagram != '' ORDER BY date DESC LIMIT :limit", nativeQuery = true)
    List<Event> findActiveEventsWithInstagramLimit(@Param("limit") int limit);
}

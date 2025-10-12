package com.conectasocial.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name = "Application", description = "Endpoints gerais da aplicação")
public class AppController {
    
    @GetMapping
    @Operation(summary = "Health check", description = "Verifica se a API está funcionando")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "API Conecta Social está funcionando!");
        response.put("timestamp", LocalDateTime.now());
        response.put("version", "1.0.0");
        response.put("status", "UP");
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/info")
    @Operation(summary = "Informações da API", description = "Retorna informações sobre a API")
    public ResponseEntity<Map<String, Object>> getInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Conecta Social API");
        response.put("description", "API para gestão de funcionários e eventos da Conecta Social");
        response.put("version", "1.0.0");
        response.put("author", "Conecta Social Team");
        response.put("documentation", "/swagger-ui.html");
        
        return ResponseEntity.ok(response);
    }
}

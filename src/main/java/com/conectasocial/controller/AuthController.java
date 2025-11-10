package com.conectasocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conectasocial.dto.ApiResponse;
import com.conectasocial.dto.auth.LoginRequest;
import com.conectasocial.dto.auth.LoginResponse;
import com.conectasocial.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints de autenticação")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica um usuário e retorna um token JWT")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginData = authService.login(loginRequest);
            ApiResponse<LoginResponse> response = ApiResponse.success(loginData);
        return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<LoginResponse> errorResponse = ApiResponse.error(401, "Credenciais inválidas");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
    
}

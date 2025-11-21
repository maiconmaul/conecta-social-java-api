package com.conectasocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.conectasocial.domain.entity.Employee;
import com.conectasocial.dto.auth.LoginRequest;
import com.conectasocial.dto.auth.LoginResponse;
import com.conectasocial.repository.EmployeeRepository;
import com.conectasocial.security.JwtUtil;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public LoginResponse login(LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );
            
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Employee employee = employeeRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));
            
            String token = jwtUtil.generateToken(
                employee.getId().toString(),
                employee.getEmail(),
                employee.getName(),
                employee.getSurname()
            );
            
            return new LoginResponse(token);
    }
    
    public Employee validateUser(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));
        
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
        
        return employee;
    }
}

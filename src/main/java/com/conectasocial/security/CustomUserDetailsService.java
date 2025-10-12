package com.conectasocial.security;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.conectasocial.domain.entity.Employee;
import com.conectasocial.repository.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee;
        
        // Verifica se é um UUID (vindo do JWT sub) ou email
        if (isValidUUID(username)) {
            UUID userId = UUID.fromString(username);
            employee = employeeRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        } else {
            employee = employeeRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        }
        
        return new User(
                employee.getEmail(),
                employee.getPassword(),
                employee.getActive(),
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + employee.getRole().name()))
        );
    }
    
    private boolean isValidUUID(String uuidString) {
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

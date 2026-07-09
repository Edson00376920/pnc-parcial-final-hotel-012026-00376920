package com.uca.pncparcialfinalhotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Requerido para que funcione el @PreAuthorize("hasAuthority('...')")
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitado para poder testear rutas en Postman sin tokens CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Endpoints públicos para Login/Registro
                        .anyRequest().authenticated() // Todo lo demás requerirá inicio de sesión
                )
                .httpBasic(httpBasic -> {}); // Habilita autenticación básica simple para pruebas del docente

        return http.build();
    }
}
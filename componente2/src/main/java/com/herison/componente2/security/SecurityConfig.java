package com.herison.componente2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v2/usuarios/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v2/usuarios/register").permitAll()
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v2/autores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v2/autores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v2/autores/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v2/livros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v2/livros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v2/livros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v2/autores").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v2/autores/nome").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v2/livros").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v2/livros/titulo").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


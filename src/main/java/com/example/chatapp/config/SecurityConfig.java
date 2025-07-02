package com.example.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public AuthenticationManager authenticationmanager(AuthenticationConfiguration config) throws Exception{
		
		return config.getAuthenticationManager();
		
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF for stateless APIs
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> 
            			sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // 2. Define URL authorization rules
            .authorizeHttpRequests(auth -> auth
                // Allow anyone to register or login
                .requestMatchers("/api/auth/register", "/api/auth/login","/api/auth/**").permitAll()
                .requestMatchers("/error").permitAll()
                // All other API calls require authentication
                .anyRequest().authenticated()
            )
            
            
            // 4. Basic auth (for testing)—replace or remove when you add JWT
            .httpBasic(Customizer.withDefaults());
//            .debug(true);
        
        return http.build();
    }
}

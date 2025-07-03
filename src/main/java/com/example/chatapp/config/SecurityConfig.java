package com.example.chatapp.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.chatapp.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@Bean
    public UserDetailsService userDetailsService() {
        return username -> 
        		userRepository.findByUsername(username)
        		.map(user -> org.springframework.security.core.userdetails.User
        	            .withUsername(user.getUsername())
        	            .password(user.getPassword())
        	            .authorities("ROLE_USER")
        	            .build() // <-- move it here
        	        )
        	        .orElseThrow(() ->
        	            new UsernameNotFoundException("User not found: " + username)
        	        );
    }

    /**
     * 2) Build an AuthenticationManager that actually uses that service + encoder
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
            .getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder)
            .and()
            .build();
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

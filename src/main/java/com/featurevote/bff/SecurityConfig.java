package com.featurevote.bff;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/**").permitAll() // Allow all requests to the root and static resources
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity; enable in production
                .cors(Customizer.withDefaults()) // Enable CORS support
                .formLogin(Customizer.withDefaults()) // Enable form-based login; you can customize this
                .logout(Customizer.withDefaults()) // Enable logout support
                .httpBasic(withDefaults()); // Or .formLogin(withDefaults());
        return http.build();
    }
}

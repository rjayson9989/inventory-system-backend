package dev.dlsu.inventorysystembackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import dev.dlsu.inventorysystembackend.service.EmployeeUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final EmployeeUserDetailsService employeeUserDetailsService;

    public SecurityConfig(EmployeeUserDetailsService employeeUserDetailsService) {
        this.employeeUserDetailsService = employeeUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests(
                       auth -> auth.mvcMatchers(HttpMethod.POST, "/api/employee").permitAll()
                                   .anyRequest().authenticated()
                    )
            .userDetailsService(employeeUserDetailsService)
            .headers(
                       header -> header.frameOptions().sameOrigin()
                    )
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

package com.studentapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Lazy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection with @Lazy on PasswordEncoder
    public SecurityConfig(UserDetailsService userDetailsService, @Lazy PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/logout")  // Disables CSRF protection for logout
            )
            .authorizeHttpRequests(auth -> 
                auth.requestMatchers("/login", "/register", "/resources/**", "/css/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> 
                form.loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .permitAll()
            )
            .logout(logout -> 
                logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.denisselitsky.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration

public class MySecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("denis").password("denis").roles("EMPLOYEE").build());
        manager.createUser(users.username("kate").password("kate").roles("MANAGER","EMPLOYEE").build());
        return manager;
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").hasAnyRole("EMPLOYEE", "MANAGER" , "HR")
                        .requestMatchers("/hr_info/**").hasRole("HR")
                        .requestMatchers("/manager_info/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());

        return http.build();
    }

}
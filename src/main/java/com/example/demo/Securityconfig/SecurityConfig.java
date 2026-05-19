package com.example.demo.Securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//
//                .authorizeHttpRequests(auth -> auth
//
//                        .requestMatchers("/auth/register").permitAll()
//                        .requestMatchers("/projects/**").permitAll() // 🔥 FIX
//
//                        .requestMatchers("/bids/place").hasRole("FREELANCER")
//                        .requestMatchers("/submissions/submit").hasRole("FREELANCER")
//
//                        .requestMatchers("/reviews/add").hasRole("CLIENT")
//                        .requestMatchers("/bugs").hasRole("CLIENT")
//                        .requestMatchers("/bugs/**").authenticated()
//                        .requestMatchers("/bids/accept").hasRole("CLIENT")
//
//                        .anyRequest().authenticated()
//                )
//
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//}
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 🔥 allow everything
                );

        return http.build();
    }
}
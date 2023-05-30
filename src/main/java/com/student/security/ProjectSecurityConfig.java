package com.student.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    DefaultSecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/student/create").authenticated()
                .requestMatchers("/student/all").authenticated()
                .requestMatchers("/student/{rollNo}").authenticated()
                .requestMatchers("/student/welcome").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .and().httpBasic();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("bhushan")
                .password("bhushan123")
                .roles("ADMIN")
                .build();
        UserDetails student=User.withDefaultPasswordEncoder()
                .username("saloni")
                .password("saloni123")
                .roles("STUDENT")
                .build();
        return new InMemoryUserDetailsManager(admin,student);

    }
}

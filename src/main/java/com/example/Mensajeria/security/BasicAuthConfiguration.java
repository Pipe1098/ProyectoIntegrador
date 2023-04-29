package com.example.Mensajeria.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class BasicAuthConfiguration {

    @Value("${usuario}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${admin}")
    private String adminName;

    @Value("${password2}")
    private String adminPassword;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).authenticated()
                .and().csrf().disable().build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername(userName)
                        .password(passwordEncoder().encode(password))
                        .authorities("read","ROLE_USER")
                        .build(),
                User.withUsername(adminName)
                        .password(passwordEncoder().encode(adminPassword))
                        .authorities("read", "write", "ROLE_ADMIN")
                        .build(),
                User.withUsername("dba")
                        .password(passwordEncoder().encode("dba123"))
                        .authorities("read", "ROLE_DBA")
                        .build()
        );
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}




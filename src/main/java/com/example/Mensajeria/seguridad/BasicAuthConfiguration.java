package com.example.Mensajeria.seguridad;




import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicAuthConfiguration {

/*
    @Value("${usuario}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    AuthenticationManager authManager) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                //.antMatchers("/api/v1/cliente").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles("READ")
                .build()
        );
        return manager;
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
*/

}




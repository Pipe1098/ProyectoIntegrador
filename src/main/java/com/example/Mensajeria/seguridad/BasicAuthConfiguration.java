package com.example.Mensajeria.seguridad;




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


    //Endpoint level authorization

    // ---- Matcher
    // 1. AnyRequest
    // 2. RequestMatchers
    // 3. RequestMatchers with HttpMethod


    // ---- Authorization rule
    // 1. PermitAll
    // 2. DenyAll
    // 3. Authenticated
    // 4. HasRole
    // 5. HasAuthority
    // 6. Access (SpEL) - Spring Expression Language

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and().authorizeHttpRequests()
                //.anyRequest().permitAll()
//                .requestMatchers("/demo").permitAll()
//                .requestMatchers("/admin").hasRole("ADMIN")
//                .requestMatchers("/dba").hasAnyRole("DBA", "ADMIN")
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).authenticated()
                .and().csrf().disable().build();
    }



//    @Bean
//    public SecurityFilterChain1 securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic()
//                .and().authorizeHttpRequests()
//                //.anyRequest().permitAll()
//                //.anyRequest().denyAll()
//                //.anyRequest().authenticated()
//                //.anyRequest().hasRole("ADMIN")
//                //.anyRequest().hasAuthority("write")
//                .anyRequest().access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('DBA')")) //Spring Expression Language (SpEL)
//                .and().build();

//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .authorities("read","ROLE_USER")
                        .build(),
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
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




package com.fsd09.programming3.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authz-> //TODO finish the register page
                        authz.requestMatchers("/public/**","/user/register")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(formLogin-> //TODO finish the login,index,and 401 html pages
                        formLogin.loginPage("/login")
                                .successForwardUrl("/index")
                                .failureForwardUrl("/401")
                                .permitAll()
                                )
                .authenticationProvider(authenticationProvider);
        return http.build();
    }
}

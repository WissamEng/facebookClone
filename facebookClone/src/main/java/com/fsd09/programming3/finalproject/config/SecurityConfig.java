package com.fsd09.programming3.finalproject.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.naming.AuthenticationException;
import java.util.Arrays;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationFailureHandler failureHandler;

    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider, AuthenticationFailureHandler failureHandler) {
        this.authenticationProvider = authenticationProvider;
        this.failureHandler = failureHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authz-> //TODO finish the register page
                        authz.requestMatchers("/public/**","/user/register","/js/**,/doLogin,")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(loginForm->loginForm
                        .loginPage("/login_page")
                        .loginProcessingUrl("/doLogin")
                        .successForwardUrl("/home")
                        .failureHandler(failureHandler)
                        .permitAll())
                .logout(logout->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login_page")
                                .invalidateHttpSession(true))

                .authenticationProvider(authenticationProvider);
        return http.build();
    }
}

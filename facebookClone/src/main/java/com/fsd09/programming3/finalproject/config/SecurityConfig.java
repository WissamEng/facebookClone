package com.fsd09.programming3.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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
                .authorizeHttpRequests(authz->
                        authz.requestMatchers("/public/**","/user/register","/js/**","/doLogin","/login_page")
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

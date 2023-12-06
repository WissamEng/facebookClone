package com.fsd09.programming3.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                        authz.requestMatchers("/public/**","/user/register","/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
//                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .formLogin(loginForm->loginForm
                        .loginPage("/login_page")
                        .loginProcessingUrl("/doLogin")
                        .successForwardUrl("/home")
                        .permitAll())
                .authenticationProvider(authenticationProvider);
        return http.build();
    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}

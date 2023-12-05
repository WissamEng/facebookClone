package com.fsd09.programming3.finalproject.config;

import com.fsd09.programming3.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Configuration
public class SecurityConfigBean {
    private final UserRepository userRepository;

    @Autowired

    public SecurityConfigBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    @Transactional
    public UserDetailsService userDetailsServiceImp() {
        return username -> new UserDetailsImp(
                userRepository.findByUserNameIgnoreCase(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user can not be found")));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImp());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

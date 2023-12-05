package com.fsd09.programming3.finalproject.config;

import com.fsd09.programming3.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImp(userRepository.findByUserNameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User can bot be found")));
    }
}

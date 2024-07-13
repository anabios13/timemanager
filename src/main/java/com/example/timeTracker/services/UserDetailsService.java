package com.example.timeTracker.services;


import com.example.timeTracker.models.User;
import com.example.timeTracker.repositories.UserRepository;
import com.example.timeTracker.security.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String login) throws BadCredentialsException {
        Optional<User> user = userRepository.findByUsername(login);
        if (user.isEmpty()) {
            throw new BadCredentialsException("User Not Found!");
        }
        return new UserDetails(user.get());
    }
}

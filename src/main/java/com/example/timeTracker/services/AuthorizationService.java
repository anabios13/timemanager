package com.example.timeTracker.services;

import com.example.timeTracker.DTO.AuthResponseDTO;
import com.example.timeTracker.DTO.AuthenticationDTO;
import com.example.timeTracker.models.User;
import com.example.timeTracker.repositories.UserRepository;
import com.example.timeTracker.security.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public AuthorizationService(UserRepository userRepository, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<AuthResponseDTO> performLogin(AuthenticationDTO authenticationDTO) throws BadCredentialsException {
        try {
            if(authenticationDTO.getPassword().isEmpty())
                throw new BadCredentialsException("Password can`t be empty!");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            authentication = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword(),authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(authenticationDTO.getLogin());
            String authorityForResponse = authentication.getAuthorities().toString();
            ResponseCookie responseCookie = ResponseCookie.from("token", token)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(24 * 60 * 60)
                    .build();
            User user = userRepository.findByUsername(authentication.getName()).stream().findAny().orElse(null);
            AuthResponseDTO authResponseDTO = new AuthResponseDTO("Login successful", authorityForResponse,user.getUsername());

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .body(authResponseDTO);
        } catch (BadCredentialsException e) {
            AuthResponseDTO authResponseDTO = new AuthResponseDTO("Incorrect credentials", null,null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}


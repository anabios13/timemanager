package com.example.timeTracker.controllers;

import com.example.timeTracker.DTO.AuthenticationDTO;
import com.example.timeTracker.DTO.UserDTO;
import com.example.timeTracker.services.AuthorizationService;
import com.example.timeTracker.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    private final AuthorizationService authorizationService;


    public AuthController(RegistrationService registrationService, AuthorizationService authorizationService) {
        this.registrationService = registrationService;
        this.authorizationService = authorizationService;

    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> performRegistration(@RequestBody UserDTO userDTO) {
        return registrationService.performRegistration(userDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws BadCredentialsException {
         return authorizationService.performLogin(authenticationDTO);
    }
}

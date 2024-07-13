package com.example.timeTracker.services;

import com.example.timeTracker.DTO.UserDTO;
import com.example.timeTracker.mappers.UserMapper;
import com.example.timeTracker.models.User;
import com.example.timeTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

//    public UserDTO createUser(UserDTO userDTO) {
//        User user = userMapper.toEntity(userDTO);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userMapper.toDTO(userRepository.save(user));
//    }

//    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
//        return userRepository.findById(id).map(existingUser -> {
//            existingUser.setUsername(userDTO.getUsername());
//            existingUser.setRole(userDTO.getRole());
//            if (userDTO.getPassword() != null) {
//                existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//            }
//            return userMapper.toDTO(userRepository.save(existingUser));
//        });
//    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

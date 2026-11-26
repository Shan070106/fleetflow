package com.example.fleetflow.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.IllegalArgumentException;

import com.example.fleetflow.entity.User;
import com.example.fleetflow.enums.Role;
import com.example.fleetflow.dto.UserCreateDTO;
import com.example.fleetflow.dto.UserResponseDTO;
import com.example.fleetflow.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserResponseDTO convertToDTO(User user) {

        return new UserResponseDTO(
            user.getId(), 
            user.getName(), 
            user.getEmail(),
            user.getRole(),
            user.isEnabled(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );

    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll()
                    .stream()
                    .map(this::convertToDTO)
                    .toList() ;
    }

    public Optional<UserResponseDTO> getUserById(Long id){
        return userRepository.findById(id)
                    .map(this::convertToDTO);
    }

    public Optional<UserResponseDTO> getUserByEmail(String email){
        return userRepository.findByEmail(email)
                    .map(this::convertToDTO);
    }

    public List<UserResponseDTO> getUsersByRole(Role role){
        return userRepository.findByRole(role)
                    .stream()
                    .map(this::convertToDTO)
                    .toList() ;
    }

    public List<UserResponseDTO> getUsersByStatus(boolean enabled){
        return userRepository.findByEnabled(enabled)
                    .stream()
                    .map(this::convertToDTO)
                    .toList();
    }    

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){

        if(userRepository.existsByEmail(userCreateDTO.getEmail())){
            throw new IllegalArgumentException("Email is already registered");
        }
        
        String encodedPassword = passwordEncoder.encode(userCreateDTO.getPassword());

        User user = User.createUser(
            userCreateDTO.getName(), 
            userCreateDTO.getEmail(), 
            encodedPassword, 
            userCreateDTO.getRole()
        );

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }
}

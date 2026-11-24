package com.example.fleetflow.service;

import org.springframework.stereotype.Service;

import com.example.fleetflow.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import com.example.fleetflow.entity.User;
import com.example.fleetflow.enums.Role;
import com.example.fleetflow.dto.UserResponseDTO;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}

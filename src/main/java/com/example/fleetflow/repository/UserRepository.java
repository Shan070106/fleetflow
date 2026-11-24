package com.example.fleetflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fleetflow.entity.User;
import com.example.fleetflow.enums.Role;

import java.util.Optional;
import java.util.List;

@Repository 
public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
    List<User> findByRole(Role role);

    List<User> findByEnabled(boolean enabled);
} 

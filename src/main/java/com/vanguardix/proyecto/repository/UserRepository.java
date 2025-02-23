package com.vanguardix.proyecto.repository;

import com.vanguardix.proyecto.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    // Método para buscar por email
    User findByEmail(String email);
}


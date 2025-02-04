package com.vanguardix.proyecto.service;

import com.vanguardix.proyecto.model.User;
import com.vanguardix.proyecto.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final RepositoryUser RepositoryUser;

    public UserService(RepositoryUser RepositoryUser) {
        this.RepositoryUser = RepositoryUser;
    }

    public List<User> getAllUsers() {
        return RepositoryUser.findAll();
    }

    public Optional<User> getUserById(int id) {
        return RepositoryUser.findById(id);
    }

    public User saveUser(User user) {
        return RepositoryUser.save(user);
    }

    public void deleteUser(int id) {
        RepositoryUser.deleteById(id);
    }
}

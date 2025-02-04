package com.vanguardix.proyecto.repository;

import com.vanguardix.proyecto.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryUser {
    private final List<User> users = new ArrayList<>();

    public RepositoryUser(){
        users.add(new User(1, "Andrés", "andres.montes@mail.escuelaing.edu.co"));
        users.add(new User(2, "Carolina", "Carolina@mail.escuelaing.edu.co"));
        users.add(new User(3, "Cristian", "Cristian@mail.escuelaing.edu.co"));
    }
    public List<User> findAll(){
        return users;
    }

    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public boolean existsById(int id) {
        return users.stream().anyMatch(user -> user.getId() == id);
    }

    public User save(User user) {
        if (existsById(user.getId())) {
            throw new IllegalArgumentException("El ID " + user.getId() + " ya está en uso.");
        }
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        users.removeIf(user -> user.getId() == id);
    }

}

package com.example.createrestapispringboot.service;

import com.example.createrestapispringboot.model.Users;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<Users> findUserByEmail(String email);

    List<Users> getAllUsers();

    void createUser(Users user);

    void saveUser(Users user) throws NoSuchAlgorithmException;

    Users updateUser(int id, Users user);

    void deleteUser(int id);

    Optional<Users> findUserById(int id);
}

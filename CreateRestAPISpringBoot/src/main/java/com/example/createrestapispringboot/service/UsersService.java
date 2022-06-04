package com.example.createrestapispringboot.service;

import com.example.createrestapispringboot.exception.DuplicateRecordException;
import com.example.createrestapispringboot.exception.InternalServerException;
import com.example.createrestapispringboot.exception.ResourceNotFoundException;
import com.example.createrestapispringboot.model.Users;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<Users> findUserByEmail(String email);

    List<Users> getAllUsers();

    void createUser(Users user) throws DuplicateRecordException;

    void saveUser(Users user) throws NoSuchAlgorithmException;

    Users updateUser(int id, Users user) throws InternalServerException, ResourceNotFoundException;

    void deleteUser(int id) throws ResourceNotFoundException;

    Optional<Users> findUserById(int id);
}

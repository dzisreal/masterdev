package com.example.createrestapispringboot.service.impl;


import com.example.createrestapispringboot.exception.DuplicateRecordException;
import com.example.createrestapispringboot.exception.InternalServerException;
import com.example.createrestapispringboot.exception.ResourceNotFoundException;
import com.example.createrestapispringboot.model.Users;
import com.example.createrestapispringboot.repository.UsersRepository;
import com.example.createrestapispringboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    //Encrypt password by md5 algoth
    public String encryptPassWord(String passWord) throws NoSuchAlgorithmException {
        byte[] bytesOfPassWord = passWord.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(bytesOfPassWord);
        String md5PassWord = new String(md5);
        return md5PassWord;
    }

    @Autowired private UsersRepository usersRepository;

    @Override
    public Optional<Users> findUserById(int id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<Users> findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<Users> getAllUsers(){
        return (List<Users>) usersRepository.findAll();
    }

    @Override
    public void saveUser(Users user) throws NoSuchAlgorithmException {
        user.setPassword(encryptPassWord(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public void createUser(Users user) {
        if (this.findUserById(user.getId()).isPresent()){
            throw new DuplicateRecordException("User co Id " + user.getId() + " da ton tai.");
        }
        if (this.findUserByEmail(user.getEmail()).isPresent()){
            throw new DuplicateRecordException("User co email " + user.getEmail() + " da ton tai.");
        }
        usersRepository.save(user);
    }

    @Override
    public Users updateUser(int id, Users user) throws ResourceNotFoundException {
        if (!Objects.equals(id, user.getId())){
            throw new ResourceNotFoundException("Id khong ton tai.");
        }
        try {
            usersRepository.save(user);
        } catch (Exception e){
            throw new InternalServerException("Loi khi ket noi toi Database.");
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        if (usersRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("User co id " + id + " khong ton tai.");
        }
        usersRepository.deleteById(id);
    }


}

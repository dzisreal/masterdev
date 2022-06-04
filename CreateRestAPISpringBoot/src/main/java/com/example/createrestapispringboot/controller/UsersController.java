package com.example.createrestapispringboot.controller;

import com.example.createrestapispringboot.exception.ResourceNotFoundException;
import com.example.createrestapispringboot.model.Users;
import com.example.createrestapispringboot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private  UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //Encrypt password by md5 algoth
    public String encryptPassWord(String passWord) throws NoSuchAlgorithmException {
        byte[] bytesOfPassWord = passWord.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(bytesOfPassWord);
        String md5PassWord = new String(md5);
        return md5PassWord;
    }

    @GetMapping
    public List<Users> getALLUsers(){
        return usersRepository.findAll();
    }

    //Create users RestAPI
    @PostMapping("/create")
    public Users createUsers(@RequestBody Users user) throws NoSuchAlgorithmException {
        String pw = user.getPassword();
        pw = encryptPassWord(pw);
        user.setPassword(pw);
        return usersRepository.save(user);
    }

    //Get users's infor by Id RestAPI
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable int id) throws ResourceNotFoundException {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay User co id: " + id));
        return ResponseEntity.ok(user);
    }

    //Update users's infor by Id RestAPI
    @PutMapping("/update/{id}")
    public  ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users userDetails) throws ResourceNotFoundException {
        Users updateUser = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User co id " + id + " khong ton tai"));
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setPassword(userDetails.getPassword());

        usersRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    // delete user RestAPI
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) throws ResourceNotFoundException {

        Users employee = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User co id " + id + " khong ton tai"));

        usersRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

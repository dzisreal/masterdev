package com.example.createrestapispringboot;

import com.example.createrestapispringboot.model.Users;
import com.example.createrestapispringboot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateRestApiSpringBootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CreateRestApiSpringBootApplication.class, args);
    }

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {
    }
}

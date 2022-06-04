package com.example.createrestapispringboot.repository;

import com.example.createrestapispringboot.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users,Integer> {
}

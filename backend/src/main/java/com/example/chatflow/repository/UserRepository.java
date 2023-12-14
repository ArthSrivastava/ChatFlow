package com.example.chatflow.repository;

import com.example.chatflow.entity.Status;
import com.example.chatflow.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}

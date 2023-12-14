package com.example.chatflow.repository;

import com.example.chatflow.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndReceiverId(String senderId, String recipientId);
}

package com.example.chatflow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String receiverId;
}

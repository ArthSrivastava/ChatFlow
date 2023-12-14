package com.example.chatflow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    @Id
    private String id;
    private String chatId;
    private String content;
    private String senderId;
    private String receiverId;
    private LocalDateTime localDateTime;
}

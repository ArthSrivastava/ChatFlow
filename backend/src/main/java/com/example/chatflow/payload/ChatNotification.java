package com.example.chatflow.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotification {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
}

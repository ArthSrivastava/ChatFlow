package com.example.chatflow.controller;

import com.example.chatflow.entity.ChatMessage;
import com.example.chatflow.payload.ChatNotification;
import com.example.chatflow.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatMessage savedChatMsg = chatMessageService.save(chatMessage);

        //the private queue url would be like this -> /user/{nickname}/queue/messages
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                new ChatNotification(
                        savedChatMsg.getId(),
                        savedChatMsg.getSenderId(),
                        savedChatMsg.getReceiverId(),
                        savedChatMsg.getContent()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable String senderId,
            @PathVariable String receiverId
    ) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
    }


}

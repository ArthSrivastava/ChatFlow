package com.example.chatflow.service;

import com.example.chatflow.entity.ChatMessage;
import com.example.chatflow.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getReceiverId(), true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatRoomId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatRoomId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}

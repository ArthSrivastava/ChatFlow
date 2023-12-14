package com.example.chatflow.service;

import com.example.chatflow.entity.ChatRoom;
import com.example.chatflow.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(String senderId, String receiverId, boolean newRoom) {
        return chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (newRoom) {
                        String chatId = createRoom(senderId, receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }
    public String createRoom(
            String senderId,
            String receiverId
    ) {
        String chatId = String.format("%s_%s", senderId, receiverId);
        ChatRoom sender2Receiver = ChatRoom.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .chatId(chatId)
                .build();
        ChatRoom receiver2Sender = ChatRoom.builder()
                .senderId(receiverId)
                .receiverId(senderId)
                .chatId(chatId)
                .build();
        chatRoomRepository.save(sender2Receiver);
        chatRoomRepository.save(receiver2Sender);
        return chatId;
    }



}

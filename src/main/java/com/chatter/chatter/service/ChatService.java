package com.chatter.chatter.service;

import com.chatter.chatter.dto.ChatMessage;
import com.chatter.chatter.enums.MessageType;
import com.chatter.chatter.kafka.ChatKafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatKafkaProducer chatKafkaProducer;
    private final ChatHistoryService chatHistoryService;

    public void onMessage(ChatMessage chatMessage) {
        String sender = safeTrim(chatMessage.sender());
        String content = safeTrim(chatMessage.content());
        String roomId = safeTrim(chatMessage.roomId());

        if (roomId != null && roomId.isBlank()) {
            roomId = null;
        }

        MessageType messageType = chatMessage.messageType() != null
                ? chatMessage.messageType()
                : MessageType.CHAT;

        ChatMessage normalized = new ChatMessage(
                messageType,
                sender,
                null,
                roomId,
                content != null && content.length() > 1000 ? content.substring(0, 1000) : content,
                Instant.now().toString()
        );

        if (roomId == null && messageType == MessageType.CHAT) {
            chatHistoryService.saveMessage(normalized);
        }

        chatKafkaProducer.publish(normalized);
    }

    private static String safeTrim(String text) {
        return text == null ? null : text.strip();
    }
}
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

    public void onMessage(ChatMessage chatMessage){
        String sender = safeTrim(chatMessage.sender());
        String content = safeTrim(chatMessage.content());
        MessageType type = chatMessage.messageType() != null ? chatMessage.messageType() : MessageType.CHAT;

        String sentAt = Instant.now().toString();

        ChatMessage normalized = new ChatMessage(
                type,
                sender,
                null, // disabled for now
                null, // disabled for now
                content != null && content.length() > 1000 ? content.substring(0, 1000) : content,
                sentAt
        );

        chatKafkaProducer.publish(normalized);
    }

    private static String safeTrim(String text) {
        return text == null ? null : text.strip();
    }
}

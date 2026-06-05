package com.chatter.chatter.kafka;

import com.chatter.chatter.dto.ChatMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatKafkaConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "chat.messages")
    public void consume(ChatMessage chatMessage) {
        String roomId = chatMessage.roomId();
        String destination = (roomId != null && !roomId.isBlank())
                ? "/topic/rooms/" + roomId.trim()
                : "/topic/public";

        messagingTemplate.convertAndSend(destination, chatMessage);
    }

    @KafkaListener(topics = "chat.room.messages")
    public void consumeRoomMessage(ChatMessage chatMessage) {
        String roomId = chatMessage.roomId();
        if (roomId == null || roomId.isBlank()) return;

        messagingTemplate.convertAndSend("/topic/room/" + roomId.trim(), chatMessage);
    }
}
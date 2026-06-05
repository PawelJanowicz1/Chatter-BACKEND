package com.chatter.chatter.kafka;

import com.chatter.chatter.dto.ChatMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatKafkaProducer {
    private static final String PUBLIC_TOPIC = "chat.messages";
    private static final String ROOM_TOPIC = "chat.room.messages";

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public ChatKafkaProducer(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ChatMessage message) {
        kafkaTemplate.send(PUBLIC_TOPIC, message);
    }

    public void publishToRoom(ChatMessage message, String roomId) {
        kafkaTemplate.send(ROOM_TOPIC, roomId, message);
    }
}
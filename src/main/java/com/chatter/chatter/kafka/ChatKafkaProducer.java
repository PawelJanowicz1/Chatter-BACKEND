package com.chatter.chatter.kafka;

import com.chatter.chatter.dto.ChatMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatKafkaProducer {
    private static final String TOPIC = "chat.messages";
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public ChatKafkaProducer(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ChatMessage message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
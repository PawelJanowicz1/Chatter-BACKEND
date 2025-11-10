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
    public void consume(ChatMessage message) {
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}
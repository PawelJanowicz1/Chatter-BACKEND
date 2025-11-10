package com.chatter.chatter.controller;

import com.chatter.chatter.dto.ChatMessage;
import com.chatter.chatter.service.ChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat")
    public void onMessage(@Valid ChatMessage incoming) {
        chatService.onMessage(incoming);
    }
}
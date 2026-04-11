package com.chatter.chatter.controller;

import com.chatter.chatter.dto.ChatMessage;
import com.chatter.chatter.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatHistoryService chatHistoryService;

    @GetMapping("/messages/public")
    public List<ChatMessage> getPublicMessages(){
        return chatHistoryService.getPublicMessages();
    }
}

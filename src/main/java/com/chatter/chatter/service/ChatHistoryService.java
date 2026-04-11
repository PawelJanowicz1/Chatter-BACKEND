package com.chatter.chatter.service;

import com.chatter.chatter.dto.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatHistoryService {
    private static final int MAX_PUBLIC_MESSAGES = 100;

   private final List<ChatMessage> publicMessages = new ArrayList<>();

    public synchronized void saveMessage(ChatMessage chatMessage){
        publicMessages.add(chatMessage);

        if(publicMessages.size() > MAX_PUBLIC_MESSAGES){
            publicMessages.remove(0);
        }
    }

    public synchronized List<ChatMessage> getPublicMessages(){
        return new ArrayList<>(publicMessages);
    }

    public synchronized void clearPublicMessages(){
        publicMessages.clear();
    }
}

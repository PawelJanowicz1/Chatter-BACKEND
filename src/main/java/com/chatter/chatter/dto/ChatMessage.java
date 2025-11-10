package com.chatter.chatter.dto;

import com.chatter.chatter.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChatMessage(@NotNull MessageType messageType, @NotBlank String sender, String recipient, String roomId, @Size(max = 1000) String content, String sentAt) {
}

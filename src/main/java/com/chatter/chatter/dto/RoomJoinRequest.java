package com.chatter.chatter.dto;

import jakarta.validation.constraints.NotBlank;

public record RoomJoinRequest(@NotBlank String password) {}
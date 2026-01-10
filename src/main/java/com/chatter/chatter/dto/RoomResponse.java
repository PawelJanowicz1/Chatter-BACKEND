package com.chatter.chatter.dto;

public record RoomResponse(Long id,
                           String name,
                           Long maxCapacity,
                           boolean isPrivate) {
}
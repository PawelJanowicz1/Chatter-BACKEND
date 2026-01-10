package com.chatter.chatter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record RoomCreateRequest(@NotBlank String name,
                                @PositiveOrZero Long maxCapacity,
                                boolean isPrivate) {
}
package com.chatter.chatter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RoomCreateRequest(@NotBlank String name,
                                @PositiveOrZero Long maxCapacity,
                                boolean isPrivate,
                                @Size(min = 3) String password) {
}
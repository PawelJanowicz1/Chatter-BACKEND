package com.chatter.chatter.controller;

import com.chatter.chatter.dto.RoomCreateRequest;
import com.chatter.chatter.dto.RoomResponse;
import com.chatter.chatter.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/create-room")
    public RoomResponse createRoom(@Valid @RequestBody RoomCreateRequest request) {
        return roomService.createRoom(request);
    }

    @GetMapping("/rooms")
    public List<RoomResponse> listAllRooms() {
        return roomService.listAllRooms();
    }
}
package com.chatter.chatter.controller;

import com.chatter.chatter.dto.RoomCreateRequest;
import com.chatter.chatter.dto.RoomJoinRequest;
import com.chatter.chatter.dto.RoomResponse;
import com.chatter.chatter.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/rooms/{roomId}/join")
    public ResponseEntity<Void> joinRoom(@PathVariable Long roomId, @Valid @RequestBody RoomJoinRequest request) {
        roomService.joinRoom(roomId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms/{roomId}")
    public RoomResponse getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }
}
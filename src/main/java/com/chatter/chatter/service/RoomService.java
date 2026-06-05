package com.chatter.chatter.service;
import com.chatter.chatter.dto.RoomJoinRequest;
import com.chatter.chatter.model.Room;
import com.chatter.chatter.dto.RoomCreateRequest;
import com.chatter.chatter.dto.RoomResponse;
import com.chatter.chatter.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomResponse createRoom(RoomCreateRequest request) {
        Room room = Room.builder()
                .name(request.name().trim())
                .maxCapacity(request.maxCapacity())
                .isPrivate(request.isPrivate())
                .password(request.isPrivate() ? request.password() : null)
                .build();

        Room savedRoom = roomRepository.save(room);
        return map(savedRoom);
    }

    public void joinRoom(Long roomId, RoomJoinRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        if (Boolean.TRUE.equals(room.getIsPrivate())) {
            if (!room.getPassword().equals(request.password())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
            }
        }
    }

    public List<RoomResponse> listAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public List<RoomResponse> listPublicRooms() {
        return roomRepository.findByIsPrivateFalse()
                .stream()
                .map(this::map)
                .toList();
    }

    private RoomResponse map(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getMaxCapacity(),
                room.getIsPrivate()
        );
    }

    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
        return map(room);
    }
}

package com.chatter.chatter.service;

import com.chatter.chatter.dto.RoomCreateRequest;
import com.chatter.chatter.dto.RoomResponse;
import com.chatter.chatter.repository.RoomRepository;
import com.chatter.chatter.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
                .build();

        Room saved = roomRepository.save(room);

        return map(saved);
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
}

package com.chatter.chatter.repository;

import com.chatter.chatter.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByIsPrivateFalse();
    List<Room> findByIsPrivateTrue();
}
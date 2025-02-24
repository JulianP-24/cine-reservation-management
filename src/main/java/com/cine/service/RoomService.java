package com.cine.service;

import com.cine.model.Room;
import com.cine.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void deleteRoom(int id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new RuntimeException("La sala con ID " + id + " no existe.");
        }
    }

    public Room updateRoom(int id, Room roomeDetails) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Película no encontrada"));

        room.setName(roomeDetails.getName());
        room.setCapacity(roomeDetails.getCapacity());

        return roomRepository.save(room);
    }
}

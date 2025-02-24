package com.cine.controller;

import com.cine.model.Room;
import com.cine.service.RoomService;
import com.cine.util.AppSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/find-rooms")
    public ResponseEntity<?> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-rooms")
    public ResponseEntity<?> newRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.addRoom(room), HttpStatus.OK);
    }

    @DeleteMapping("/delete-room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable int id) {
        try {
            roomService.deleteRoom(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-room/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable int id, @RequestBody Room room) {
        Room updatedRoom = roomService.updateRoom(id, room);
        return ResponseEntity.ok(updatedRoom);
    }
}

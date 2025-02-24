package com.cine.controller;

import com.cine.model.Movie;
import com.cine.model.Reservartion;
import com.cine.service.EmailService;
import com.cine.service.ReservationService;
import com.cine.util.AppSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReservationController {

    private final ReservationService reservationService;
    private final EmailService emailService;

    public ReservationController(ReservationService reservationService, EmailService emailService) {
        this.reservationService = reservationService;
        this.emailService = emailService;
    }

    @GetMapping("/find-reservations")
    public ResponseEntity<?> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-reservations")
    public ResponseEntity<?> newReservation(@RequestBody Reservartion reservartion) {
        Reservartion newReservation = reservationService.newReservation(reservartion);
        if (newReservation != null) {
            emailService.sendReservationEmail(reservartion.getUserEmail(),
                    "Reserva Confirmada",
                    "Tu reserva ha sido realizada con exito");
            return new ResponseEntity<>(newReservation, HttpStatus.OK);
        };
        return new ResponseEntity<>("Error al procesar la reserva", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete-reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable int id) {
        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-reservation/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable int id, @RequestBody Reservartion reservartion) {
        Reservartion updatedReservation = reservationService.updateReservation(id, reservartion);
        return ResponseEntity.ok(updatedReservation);
    }
}

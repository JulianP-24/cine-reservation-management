package com.cine.service;

import com.cine.model.Reservartion;
import com.cine.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservartion newReservation(Reservartion reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservartion> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservation(int id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("La reserva con ID " + id + " no existe.");
        }
    }

    public Reservartion updateReservation(int id, Reservartion reservationDetails) {
        Reservartion reservartion = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Película no encontrada"));

        reservartion.setScheduleTime(reservationDetails.getScheduleTime());
        reservartion.setSeatsReserved(reservationDetails.getSeatsReserved());

        return reservationRepository.save(reservartion);
    }
}

package com.cine.repository;

import com.cine.model.Reservartion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository  extends JpaRepository<Reservartion, Integer> {
}

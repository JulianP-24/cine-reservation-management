package com.cine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESERVATIONS")
public class Reservartion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_id", nullable = false)
    private int movieId;

    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "schedule_time", nullable = false)
    private String scheduleTime;

    @Column(name = "seats_reserved", nullable = false)
    private String seatsReserved;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

}

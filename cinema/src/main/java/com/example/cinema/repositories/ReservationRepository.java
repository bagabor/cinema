package com.example.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cinema.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
}

package com.example.cinema.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;

import org.springframework.stereotype.Service;

import com.example.cinema.dao.BaseDao;
import com.example.cinema.model.Reservation;
import com.example.cinema.repositories.ReservationRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ReservationDaoImp implements BaseDao {

	@NonNull
	private final ReservationRepository reservationRepo;

	@Override
	public List<Reservation> getAll() {
		return reservationRepo.findAll();
	}

	public Reservation saveReservation(Reservation reservation) {
		return reservationRepo.save(reservation);
	}

	@Override
	public Object get(long id) throws NotSupportedException {
		throw new NotSupportedException();
	}
}

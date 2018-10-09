package com.example.cinema.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.cinema.common.BaseDao;
import com.example.cinema.model.Reservation;
import com.example.cinema.repositories.ReservationRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ReservationDaoImp implements BaseDao{
	
	@NonNull private final ReservationRepository reservationRepo;
	
	@Override
	public List<Reservation> getAll() {
		return reservationRepo.findAll();
	}
	
	public Reservation saveReservation(Reservation reservation){
		return reservationRepo.save(reservation);
	}

	@Override
	public Object get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

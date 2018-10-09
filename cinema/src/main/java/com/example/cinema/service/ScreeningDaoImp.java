package com.example.cinema.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.cinema.dao.BaseDao;
import com.example.cinema.model.Screening;
import com.example.cinema.repositories.ScreeningRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ScreeningDaoImp implements BaseDao{

	@NonNull private final ScreeningRepository screeningRepo;
	
	@Override
	public List<Screening> getAll() {
		return screeningRepo.findAll();
	}

	@Override
	public Optional<Screening> get(long id) {
		return screeningRepo.findById(id);
	}
	
	public void updateScreening(long screeningId, int reversedSeats) {
		val screening = screeningRepo.findById(screeningId); 
		screening.get().setFreeSeats(screening.get().getFreeSeats() - reversedSeats);
		screeningRepo.save(screening.get());
	}
}

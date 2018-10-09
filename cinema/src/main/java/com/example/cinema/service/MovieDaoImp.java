package com.example.cinema.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.cinema.dao.BaseDao;
import com.example.cinema.model.Movie;
import com.example.cinema.repositories.MovieRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MovieDaoImp implements BaseDao {

	@NonNull
	private final MovieRepository movieRepo;

	@Override
	public Optional<Movie> get(long id) {
		return movieRepo.findById(id);
	}

	@Override
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}
}

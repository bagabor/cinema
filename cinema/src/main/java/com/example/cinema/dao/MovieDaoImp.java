package com.example.cinema.dao;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cinema.common.BaseDao;
import com.example.cinema.model.Movie;
import com.example.cinema.repositories.MovieRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
//@Transactional
public class MovieDaoImp implements BaseDao {
	
	@NonNull private final MovieRepository movieRepo;
	
	@Override
	public Optional<Movie> get(long id) {
		return movieRepo.findById(id);
	}

	@Override
	public List<Movie> getAll() {
		return movieRepo.findAll();
	}

//	@Override
//	public void save(Object o) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void update(Object o) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Object o) {
//		// TODO Auto-generated method stub
//		
//	}

}

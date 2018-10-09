package com.example.cinema.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.cinema.repositories.MovieRepository;

public class MovieDaoTest {

	private MovieRepository movieRepo;
	private MovieDaoImp movieDaoImp;

	@Before
	public void setup() {
		movieRepo = Mockito.mock(MovieRepository.class);
		movieDaoImp = new MovieDaoImp(movieRepo);

		when(movieRepo.findById(Mockito.anyLong())).thenReturn(null);
		when(movieRepo.findAll()).thenReturn(null);
	}

	@Test(expected = NullPointerException.class)
	public void withoutAppropriateConstructor() {
		new MovieDaoImp(null);
	}

	@Test
	public void getAMovieById() {
		movieDaoImp.get(Mockito.anyLong());
		verify(movieRepo, times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getAllMovies() {
		movieDaoImp.getAll();
		verify(movieRepo, times(1)).findAll();
	}
}

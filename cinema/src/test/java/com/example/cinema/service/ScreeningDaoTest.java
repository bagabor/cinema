package com.example.cinema.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.cinema.model.Screening;
import com.example.cinema.repositories.ScreeningRepository;

public class ScreeningDaoTest {

	private ScreeningRepository screeningRepo;
	private ScreeningDaoImp screeningDaoImp;

	@Before
	public void setup() {
		screeningRepo = Mockito.mock(ScreeningRepository.class);
		screeningDaoImp = new ScreeningDaoImp(screeningRepo);

		when(screeningRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(Mockito.mock(Screening.class)));
		when(screeningRepo.findAll()).thenReturn(null);
		when(screeningRepo.save(Mockito.any())).thenReturn(null);
	}

	@Test(expected = NullPointerException.class)
	public void withoutAppropriateConstructor() {
		new ScreeningDaoImp(null);
	}

	@Test
	public void getAScreeningById() {
		screeningDaoImp.get(Mockito.anyLong());
		verify(screeningRepo, times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getAllScreening() {
		screeningDaoImp.getAll();
		verify(screeningRepo, times(1)).findAll();
	}

	@Test
	public void updateAScreening() {
		screeningDaoImp.updateScreening(0L, 0);
		verify(screeningRepo, times(1)).findById(Mockito.anyLong());
		verify(screeningRepo, times(1)).save(Mockito.any());
	}
}

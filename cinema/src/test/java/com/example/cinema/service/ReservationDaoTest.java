package com.example.cinema.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.transaction.NotSupportedException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.cinema.model.Reservation;
import com.example.cinema.repositories.ReservationRepository;

public class ReservationDaoTest {

	private ReservationRepository reservationRepository;
	private ReservationDaoImp reservationDaoImp;

	@Before
	public void setup() {
		reservationRepository = Mockito.mock(ReservationRepository.class);
		reservationDaoImp = new ReservationDaoImp(reservationRepository);

		when(reservationRepository.findById(Mockito.anyLong())).thenReturn(null);
		when(reservationRepository.findAll()).thenReturn(null);
		when(reservationRepository.save(Mockito.any())).thenReturn(null);
	}

	@Test(expected = NullPointerException.class)
	public void withoutAppropriateConstructor() {
		new ReservationDaoImp(null);
	}

	@Test(expected = NotSupportedException.class)
	public void getAReservationById() throws NotSupportedException {
		reservationDaoImp.get(0L);
	}

	@Test
	public void getAllReservations() {
		reservationDaoImp.getAll();
		verify(reservationRepository, times(1)).findAll();
	}

	@Test
	public void saveAReservation() {
		reservationDaoImp.saveReservation(Mockito.any(Reservation.class));
		verify(reservationRepository, times(1)).save(Mockito.any());
	}
}

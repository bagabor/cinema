package com.example.cinema.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.transaction.NotSupportedException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.cinema.repositories.UserRepository;

public class UserDaoTest {

	private UserRepository userRepo;
	private UserDaoImp userDaoImp;

	@Before
	public void setup() {
		userRepo = Mockito.mock(UserRepository.class);
		userDaoImp = new UserDaoImp(userRepo);

		when(userRepo.findById(Mockito.anyLong())).thenReturn(null);
		when(userRepo.findAll()).thenReturn(null);
		when(userRepo.findByuserName(Mockito.anyString())).thenReturn(null);
	}

	@Test(expected = NullPointerException.class)
	public void withoutAppropriateConstructor() {
		new MovieDaoImp(null);
	}

	@Test(expected = NotSupportedException.class)
	public void getAUserById() throws NotSupportedException {
		userDaoImp.get(0L);
		verify(userRepo, times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getAUserByUsername() {
		userDaoImp.get(Mockito.anyString());
		verify(userRepo, times(1)).findByuserName(Mockito.anyString());
	}

	@Test(expected = NotSupportedException.class)
	public void getAllUsers() throws NotSupportedException {
		userDaoImp.getAll();
		verify(userRepo, times(1)).findAll();
	}

	@Test(expected = UsernameNotFoundException.class)
	public void userAuthLoadingException() {
		userDaoImp.loadUserByUsername("");
		verify(userRepo, times(1)).findByuserName(Mockito.anyString());
	}
}

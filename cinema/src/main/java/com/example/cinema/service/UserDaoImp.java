package com.example.cinema.service;

import static java.util.Collections.emptyList;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cinema.dao.BaseDao;
import com.example.cinema.model.User;
import com.example.cinema.repositories.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UserDaoImp implements BaseDao, UserDetailsService {

	@NonNull private final UserRepository userRepo;
	
	public User get(final String userName) {
		return userRepo.findByuserName(userName);
	}

	@Override
	public Object get(long id) throws NotSupportedException{
		throw new NotSupportedException();
	}
	
	@Override
	public List<?> getAll() throws NotSupportedException{
		throw new NotSupportedException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByuserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), emptyList());
    }
}

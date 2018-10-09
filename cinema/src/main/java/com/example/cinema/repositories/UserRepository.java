package com.example.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cinema.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByuserName(String userName);
}

package com.example.cinema.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "username")
	private String userName;

	private String password;

	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private Set<Reservation> reservations;
}

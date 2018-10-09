package com.example.cinema.model;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Screening")
public class Screening{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "screening_time", columnDefinition = "DATETIME")
	@Temporal(TIMESTAMP)
	private Date time;

	@Column(name = "free_seats")
	private int freeSeats;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false, referencedColumnName = "id")
	private Movie movie;

	@JsonIgnore
	@OneToMany(mappedBy = "screening", orphanRemoval = true)
	private Set<Reservation> reservations;
}

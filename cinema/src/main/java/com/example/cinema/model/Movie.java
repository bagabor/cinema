package com.example.cinema.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.cinema.common.MovieType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Movie")
public class Movie{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String title;

	private int length;

	@Column(name = "movie_type")
	@Enumerated(STRING)
	private MovieType type;

	@OneToMany(mappedBy = "movie", fetch = EAGER, orphanRemoval = true)
	private List<Screening> screenings;
}

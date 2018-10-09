package com.example.cinema.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.model.Movie;
import com.example.cinema.service.MovieDaoImp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MovieController {

	@NonNull
	private final MovieDaoImp movieDao;

	@RequestMapping(value = "/movies", method = GET)
	@ResponseBody
	public ResponseEntity<List<Movie>> listAllMovies() {
		List<Movie> movies = movieDao.getAll();
		if (movies.isEmpty() || movies.size() == 0) {
			return new ResponseEntity<List<Movie>>(NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, OK);
	}
}

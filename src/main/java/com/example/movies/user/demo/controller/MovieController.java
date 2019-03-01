package com.example.movies.user.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.user.demo.domain.Movie;
import com.example.movies.user.repository.MovieRepository;

@RestController
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;

	@PostMapping("/movie")
	Movie newMovie(@RequestBody Movie newMovie) {

		movieRepo.insert(newMovie);
		System.out.println("Created Doc");
		return newMovie;

	}

	@PutMapping("/movie")
	Movie updateMovie(@RequestBody Movie updatedMovie) {

		Movie movie=movieRepo.findByTitle(updatedMovie.getTitle());
		if(movie != null){
			movieRepo.deleteById(movie.getTitle());
			movieRepo.save(updatedMovie);//updatedUser.getUsername());
		}
		System.out.println("Updated Doc");
		return updatedMovie;

	}

	@GetMapping("/movie")
	Movie getMovie(@RequestParam String title) {
		Movie movie=movieRepo.findByTitle(title);

		return movie;


	}



}

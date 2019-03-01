package com.azurelab.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azurelab.demo.model.Movie;
import com.azurelab.demo.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@GetMapping("/{movieId}")
	public String getMovie(@PathVariable String movieId, Model model) {
		Movie movie = movieService.getMovie(movieId);
		model.addAttribute("movie", movie);
		return "/movieDetails";
		
		
	}
}

package com.azurelab.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.azurelab.demo.model.Movie;

@Service
public class MovieService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.gateway.url}")
	String apiURL;
	
	
	public String getURL(String uri) {
		return apiURL + uri;
	}
	
	public List<String> getMovieGenres() {
		ResponseEntity<List> response = restTemplate.getForEntity(getURL("/movie/genres"), List.class);
		return response.getBody();
	}

	public Movie getMovie(String movieId) {
		ResponseEntity<Movie> response = restTemplate.getForEntity(getURL("/movie/"+movieId), Movie.class);
		return response.getBody();
	}
}


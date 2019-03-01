package com.example.movies.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.movies.user.demo.domain.Movie;


@Repository
public interface MovieRepository extends MongoRepository <Movie, String> {

	List<Movie> findByGenres(String[] genres);
	
	Movie findByTitle(String title);

 
	
//	List<Object> findByMoviePreference(String [] moviePreference);
}


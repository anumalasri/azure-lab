package com.example.movies.user.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.movies.user.demo.domain.Movie;


@Repository
public interface MovieRepository extends MongoRepository <Movie, String> {

	List<Movie> findByGenres(String[] genres);
	
	Movie findByTitle(String title);

	@Query(value="{ 'genres': { '$in' : [?0] } }", sort = "{ 'imdb.rating': -1 }", fields="{ 'title' : 1, 'year' : 1, 'imdb.rating' : 1}")
	List<Object> finduserPreferredMovies(List<String> genres, Pageable page);
	
//	List<Object> findByMoviePreference(String [] moviePreference);
}


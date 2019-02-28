package com.example.movies.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.movies.user.demo.domain.Movie;
import com.example.movies.user.demo.domain.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String userName);

//	User updateByUsername(String userName);
	List<Movie> findByMoviePreference(String [] moviePreference);
}

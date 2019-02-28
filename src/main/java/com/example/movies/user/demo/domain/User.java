package com.example.movies.user.demo.domain;

import java.util.List;
import org.springframework.data.annotation.Id;


public class User {
	
	@Id
    private  String id;
	
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<String> moviePreference;
    private List<Object> movies;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<String> getMoviePreference() {
		return moviePreference;
	}



	public void setMoviePreferences(List<String> moviePreference) {
		this.moviePreference = moviePreference;
	}


	public List<Object> getMovies() {
		return movies;
	}


	public void setMovies(List<Object> movies) {
		this.movies = movies;
	}


	public void setMoviePreference(List<String> moviePreference) {
		this.moviePreference = moviePreference;
	}
	
    

}

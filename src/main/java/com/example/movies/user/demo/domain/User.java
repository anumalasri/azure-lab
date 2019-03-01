package com.example.movies.user.demo.domain;

import java.util.List;
import org.springframework.data.annotation.Id;


public class User {
	
	@Id
    private  String _id;
	
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String[] moviePreference;
    private List<Movie> movies;


	public String getId() {
		return _id;
	}


	public void setId(String id) {
		this._id = _id;
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



	public String[] getMoviePreference() {
		return moviePreference;
	}



	public void setMoviePreferences(String[] moviePreference) {
		this.moviePreference = moviePreference;
	}


	public List<Movie> getMovies() {
		return movies;
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}


	public void setMoviePreference(String[] moviePreference) {
		this.moviePreference = moviePreference;
	}
	
    

}

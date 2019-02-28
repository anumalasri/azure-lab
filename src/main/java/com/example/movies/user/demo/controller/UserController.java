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
import com.example.movies.user.demo.domain.User;
import com.example.movies.user.repository.UserRepository;


@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {

		User username=repository.findByUsername(newUser.getUsername());
		if(username == null){
			repository.insert(newUser);
		}
		System.out.println("Created Doc");
		return newUser;

	}

	@PutMapping("/user")
	User updateUser(@RequestBody User updatedUser) {

		User username=repository.findByUsername(updatedUser.getUsername());
		if(username != null){
			repository.deleteById(username.getId());
			repository.save(updatedUser);//updatedUser.getUsername());
		}
		System.out.println("Updated Doc");
		return updatedUser;

	}
	
	@GetMapping("/user")
	List<Object> getMovies(@RequestParam String username) {
		User user=repository.findByUsername(username);

		
		return user.getMovies();
		
		
	}

}

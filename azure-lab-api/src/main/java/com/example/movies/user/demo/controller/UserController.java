package com.example.movies.user.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.user.demo.domain.User;
import com.example.movies.user.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository repository;

	@GetMapping()
	List<User> getUser() {
		return repository.findAll();
	}
	
	@GetMapping("/{username}")
	User getUserByName(@PathVariable("username") String username) {
		return repository.findByUsername(username);
	}
	
	@PostMapping
	User newUser(@RequestBody User newUser) {

		logger.info("User Request {} ", newUser);
		newUser.setId(newUser.getUsername());
		newUser.setMovies(new ArrayList<>());
		
		repository.save(newUser);
		logger.info("Created User ");
		return newUser;

	}

	@PutMapping
	User updateUser(@RequestBody User updatedUser) {

		User username=repository.findByUsername(updatedUser.getUsername());
		if(username != null){
			repository.deleteById(username.getId());
			repository.save(updatedUser);//updatedUser.getUsername());
		}
		System.out.println("Updated Doc");
		return updatedUser;

	}
	
	
	
	@GetMapping("/movies")
	List<Object> getMovies(@RequestParam String username) {
		User user=repository.findByUsername(username);
 		
		return user.getMovies();
		
		
	}

}

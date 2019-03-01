package com.azurelab.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.azurelab.demo.model.User;
import com.azurelab.demo.service.MovieService;
import com.azurelab.demo.service.UserService;

@Controller
@RequestMapping("/user")
//@SessionAttributes("user")
public class UserController {

	@Autowired
	MovieService movieService; 
	
	
	@Autowired
	UserService userService; 
	 
	@ModelAttribute("user")
    public User getUser() {
        return new User();  
	}

	@GetMapping
	public String createUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("movieTypes", movieService.getMovieGenres() );
		return "user/userCreateForm";
	}
	
	
	@PostMapping
	public String createUser(@ModelAttribute(name="user") User user, Model model) {
		
		user.getPreferences().removeAll(Arrays.asList("null",null));
		
		User user2 = userService.createUser(user);
		
		model.addAttribute("user", user2);
		return "user/userCreated";
	}
	
	
	@GetMapping("/update")
	public String updateUserForm(@SessionAttribute(name="user") User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("movieTypes", movieService.getMovieGenres() );

		return "user/userUpdateForm";
	}
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute(name="user") User user, Model model) {
		user.getPreferences().removeAll(Arrays.asList("null",null));
		
		User user2 = userService.createUser(user);
		model.addAttribute("user", user2);
		return "user/userUpdated";

	}
		
	
	
	
	@GetMapping("/home")
	public String getUserHome(@SessionAttribute("user") User user, Model model) {
	
		List<Object> movies = userService.getPreferredMovies(user.getUsername());
		
		System.out.println(movies);
		user.setMovies(movies);
		
		model.addAttribute("user", user);
	 
 		return "user/userHome";
	}
	
	
	
}

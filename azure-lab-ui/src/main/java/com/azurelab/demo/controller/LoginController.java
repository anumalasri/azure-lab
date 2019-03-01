package com.azurelab.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.azurelab.demo.model.User;
import com.azurelab.demo.service.UserService;

@Controller 
@SessionAttributes("user")
public class LoginController {

	 
	@Autowired
	UserService userService; 
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		return "loginForm";
	}
	
	@ModelAttribute("user")
    public User getUser() {
        return new User();  
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username,  Model model) {
		User user2 = userService.getUser(username); 
	    model.asMap().put("user", user2);

		if (user2 != null) {
			return "redirect:/user/home";
		}
		model.addAttribute("errorMsg", "User is not avaialble");
		return "error";
	}
	
}

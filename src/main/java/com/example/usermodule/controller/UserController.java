package com.example.usermodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.usermodule.entity.User;
import com.example.usermodule.service.CustomUserDetails;
import com.example.usermodule.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getHomepage(Model model, @AuthenticationPrincipal CustomUserDetails loggedinUser) {
		if(loggedinUser != null) model.addAttribute("username", loggedinUser.getUsername());
		return "index";
	}
	
	@GetMapping("/signup")
	public String getSignupPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String processSignup(Model model, @ModelAttribute("user") User user) {
		User newUser = userService.saveUser(user);

		if(newUser != null) {
			return "dashboard";
		} else {
			model.addAttribute("err_string", "Registration unsuccessful. Please try again.");
			return "signup";
		}
	}
	
	@GetMapping("/signin")
	public String getSigninPage() {
		return "signin";
	}
	
	@GetMapping("/dashboard")
	public String getDashboardPage(Model model, @AuthenticationPrincipal CustomUserDetails loggedinUser) {
		model.addAttribute("username", loggedinUser.getUsername());
		return "dashboard";
	}
}

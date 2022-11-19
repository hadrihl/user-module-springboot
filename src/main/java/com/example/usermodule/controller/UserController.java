package com.example.usermodule.controller;

import java.io.Console;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
			return "redirect:index";
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
	
	@GetMapping("/users")
	public String getManageUsersPage(Model model, @AuthenticationPrincipal CustomUserDetails loggedinUser) {
		model.addAttribute("username", loggedinUser.getUsername());
		
		List<User> users = userService.getAllUsers();
		
		if(users != null) {
			model.addAttribute("users", users);
		} else {
			model.addAttribute("err_string_warning", "Users not found.");
		}
		
		return "users";
	}
	
	@GetMapping("/users/{id}")
	public String enableAdminAccount(@PathVariable("id") String user_id, Model model, 
			@AuthenticationPrincipal CustomUserDetails loggedinUser ) {
		model.addAttribute("username", loggedinUser.getUsername());
		System.out.println("user_id: " + user_id); // debugging purposes
		
		User user = userService.getUserById(Integer.parseInt(user_id));
		model.addAttribute("user", user);
		return "user-type";
	}
	
	@PostMapping("/users/{id}")
	public String updateUserType(@PathVariable("id") String user_id, HttpServletRequest request) {
		String typeAdmin = request.getParameter("flexCheckAdminDefault");
		
		System.out.println("flexCheckAdminDefault: " + typeAdmin);
		userService.setAccountTypeAdmin(user_id, typeAdmin);
		
		return "redirect:";
	}
}

package com.example.usermodule.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usermodule.entity.Role;
import com.example.usermodule.entity.User;
import com.example.usermodule.repository.RoleRepository;
import com.example.usermodule.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User saveUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRoles(roleRepository.getRoleByName("USER")); // default role: USER
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Integer user_id) {
		return userRepository.findById(user_id).get();
	}
	
	public void setAccountTypeAdmin(String user_id, String enabledAdmin) {
		User user = userRepository.getReferenceById(Integer.parseInt(user_id));
		
		if(enabledAdmin == null) {
			user.removeRoles(roleRepository.findById(2).get());
		} else {
			user.setRoles(roleRepository.findById(2).get());
		}
		
		userRepository.save(user);
	}
}

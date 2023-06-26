package com.example.usermodule.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
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
	
	@Autowired
	private ImageService imageService;
	
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
	
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
	
	public void setAccountTypeAdmin(String user_id, String enabledAdmin) {
		User user = userRepository.getReferenceById(Integer.parseInt(user_id));
		
		if(enabledAdmin == null) {
			user.removeRoles(roleRepository.getRoleByName("ADMIN"));
		} else {
			user.setRoles(roleRepository.getRoleByName("ADMIN"));
		}
		
		userRepository.save(user);
	}
	
	public User updateProfile(User tmp, MultipartFile imgFile) {
		User user = userRepository.getReferenceById(tmp.getId());
		user.getId();
		user.setEmail(tmp.getEmail());
		user.setUsername(tmp.getUsername());
		
		if(imgFile.isEmpty()) {
		} else {
			String filename = imageService.uploadProfileImage(imgFile);
			user.setProfileImage(filename);
		}
		
		userRepository.save(user);
		return user;
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
}

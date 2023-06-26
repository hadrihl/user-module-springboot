package com.example.usermodule;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.example.usermodule.entity.Role;
import com.example.usermodule.entity.User;
import com.example.usermodule.repository.RoleRepository;
import com.example.usermodule.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void test_create_admin_user() {
		User user = new User();
		
		user.setUsername("admin");
		user.setEmail("admin@example.com");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		
		User savedUser = userRepository.save(user);
		User existedUser = em.find(User.class, savedUser.getId());
		
		assertThat(existedUser.getEmail()).isEqualTo(savedUser.getEmail());
	}
	
	@Test
	public void test_set_roles_to_admin_user() {
		User admin = userRepository.findUserByUsername("admin");
		
		if(admin != null) {
			Role USER = roleRepository.getRoleByName("USER");
			Role ADMIN = roleRepository.getRoleByName("ADMIN");
			
			admin.setRoles(USER);
			admin.setRoles(ADMIN);
			userRepository.save(admin);
		}
		
		Set<Role> roles = admin.getRoles();
		assertThat(roles.size()).isEqualTo(roleRepository.findAll().size());
	}

	@Test
	public void test_get_admin_user_by_email() {
		User user = userRepository.findUserByEmail("admin@example.com");
		
		User savedUser = em.find(User.class, user.getId());
		
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void test_create_generic_user() {
		User user = new User();
		user.setUsername("david");
		user.setEmail("david@example.com");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		user.setRoles(roleRepository.getRoleByName("USER"));
		
		User existedUser = userRepository.save(user);
		assertThat(existedUser.getEmail()).isEqualTo(user.getEmail());
	}
}

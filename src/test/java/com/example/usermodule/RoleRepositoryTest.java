package com.example.usermodule;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.usermodule.entity.Role;
import com.example.usermodule.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void test_create_roles() {
		Role user = new Role("USER");
		Role admin = new Role("ADMIN");
		
		List<Role> roles = roleRepository.saveAll(List.of(user, admin));
		assertThat(roles.size()).isEqualTo(2);
	}
}

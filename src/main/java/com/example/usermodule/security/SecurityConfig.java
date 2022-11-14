package com.example.usermodule.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.usermodule.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/assets/**").permitAll()
				.antMatchers(HttpMethod.GET, "/signup").permitAll()
				.antMatchers(HttpMethod.POST, "/signup").permitAll()
				.antMatchers(HttpMethod.GET, "/dashboard").hasAnyAuthority("USER", "ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/signin")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/dashboard")
				.usernameParameter("email")
				.permitAll()
				.and()
			.logout()
				.permitAll();
		
		return http.build();
	}

}

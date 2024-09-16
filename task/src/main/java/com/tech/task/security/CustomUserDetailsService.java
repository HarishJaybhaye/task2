package com.tech.task.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tech.task.entity.Student;
import com.tech.task.repository.StudentRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Student> credential = repository.findByUserName(username);

		return credential.map(CustomUserDeatils::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found with name : " + username));
	}

}

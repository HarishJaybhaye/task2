package com.tech.task.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tech.task.entity.Student;
import com.tech.task.repository.StudentRepository;

@SpringBootTest
public class StudentRepoTest {

	@Autowired
	private StudentRepository studentRepos;

	@Test
    @DisplayName("😱")
	void isUserPresrnt() throws Exception {
		
		String email = "lkjkjlksa";
		Student student = studentRepos.findByEmail("harish");
		if(null == student) {
		throw new Exception("no user");
		}
		assertEquals(email,student.getEmail());
		
	}
	
	@Test
	@Timeout(value = 12)
	void timeOut() {
		System.err.println("111");
	}
}

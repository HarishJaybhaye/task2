package com.tech.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.task.bo.StudentBo;
import com.tech.task.entity.Student;
import com.tech.task.exception.ResourceNotFoundException;
import com.tech.task.repository.StudentRepository;
import com.tech.task.security.JwtUtil;
import com.tech.task.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private JwtUtil util;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void create(Student student) throws Exception {
		Student checkStudent = studentRepo.findByEmail(student.getEmail());
		if (checkStudent != null) {
			throw new ResourceNotFoundException("404", "Student with this email already exists");
		}

		if (student.getPassword() != null) {

			String encodedPassword = passwordEncoder.encode(student.getPassword());
			student.setPassword(encodedPassword);
		} else {
			throw new IllegalArgumentException("Password cannot be null");
		}

		studentRepo.save(student);
	}

	@Override
	public void update(Student student, Long id) throws Exception {
		Student checkStudent = studentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("404", "Student not found with id: " + id));
		checkStudent.setEmail(student.getEmail());
		checkStudent.setFullName(student.getFullName());
		checkStudent.setMobile(student.getMobile());
		checkStudent.setUserName(student.getUserName());
		studentRepo.save(checkStudent);
	}

	@Override
	public Student getByStudentId(Long id) throws Exception {
		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("404", "Student not found with id: " + id));
		student.setSubjects(studentRepo.getStudentById(id));

		return student;
	}

	@Override
	public Student logIn(StudentBo studentBo) throws Exception {
		Student student = studentRepo.findByUserName(studentBo.getUserName())
				.orElseThrow(() -> new ResourceNotFoundException(HttpStatus.UNAUTHORIZED.toString(),
						"Username or Password incorrect"));

		if (!passwordEncoder.matches(studentBo.getPassword(), student.getPassword())) {
			throw new ResourceNotFoundException("401", "Username or Password incorrect");
		}

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				studentBo.getUserName(), studentBo.getPassword());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		String token = util.generateToken(student.getUserName());
		student.setSessionToken(token);

		return student;
	}

}

package com.tech.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.task.bo.Response;
import com.tech.task.bo.StudentBo;
import com.tech.task.entity.Student;
import com.tech.task.service.StudentService;

@Controller
@RequestMapping("/home")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<Response> add(@RequestBody Student student) throws Exception {
		studentService.create(student);
		Response response = new Response();
		response.setMessage("Student Added Successfully");
		response.setStatus("202");
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<Response> logIn(@RequestBody StudentBo studentBo) throws Exception {
		Response response = new Response();
		response.setMessage("login successful");
		response.setResult(studentService.logIn(studentBo));
		response.setStatus("202");

		return new ResponseEntity<Response>(response, HttpStatus.CREATED);

	}

//	@GetMapping
//	public ResponseEntity<Response> getById(@RequestParam(required = false) Long studentId) throws Exception {
//		Response response = new Response();
//		response.setResult(studentService.getByStudentId(studentId));
//		response.setMessage("Student Details");
//		response.setStatus("200");
//
//		return new ResponseEntity<Response>(response, HttpStatus.OK);
//
//	}

	@GetMapping
	public String viewHomePage(Model model, @RequestParam(required = false) Long studentId) throws Exception {
		if (studentId == null) {
			model.addAttribute("error", "Student ID is required.");
			return "index";
		}

		Student student = studentService.getByStudentId(studentId);
		if (student == null) {
			model.addAttribute("error", "Student not found.");
			return "index";
		}

		model.addAttribute("student", student);
		return "index";
	}

}

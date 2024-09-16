package com.tech.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.task.bo.Response;
import com.tech.task.entity.Subjects;
import com.tech.task.service.SubjectService;

@RestController
@RequestMapping("/home")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@PostMapping("/create")
	public ResponseEntity<String> add(@RequestBody Subjects subjects, @RequestParam(required = false) Long studentId)
			throws Exception {

		subjectService.addSubject(studentId, subjects);
		return new ResponseEntity<String>("subject Added Successfully", HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Response> getSubjectById(@RequestParam(value = ("subjectId"), required = false) Long subjectId) throws Exception {
		Response response = new Response();
		response.setResult(subjectService.getSubjectById(subjectId));
		response.setMessage("Subject Details");
		response.setStatus("200");

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}

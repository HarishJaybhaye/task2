package com.tech.task.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tech.task.entity.Student;
import com.tech.task.entity.StudentSubjectMapping;
import com.tech.task.entity.Subjects;
import com.tech.task.exception.ResourceNotFoundException;
import com.tech.task.repository.StudentSubjectMappingRepository;
import com.tech.task.repository.SubjectRepository;
import com.tech.task.service.StudentService;
import com.tech.task.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentSubjectMappingRepository mappingRepository;

	@Override
	public void addSubject(Long studentId, Subjects subjects) throws Exception {

		subjectRepo.save(subjects);
		Student student = studentService.getByStudentId(studentId);

		if (null == student) {
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.toString(), null);
		}

		StudentSubjectMapping studentSubjectMapping = new StudentSubjectMapping();
		studentSubjectMapping.setStudent(student);
		studentSubjectMapping.setSubjects(subjects);
		mappingRepository.save(studentSubjectMapping);
	}

	@Override
	public Subjects getSubjectById(Long subjectId) throws Exception {
		Subjects subjects = subjectRepo.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFoundException("404", "Student not found with id: " + subjectId));
		subjects.setStudentDTOs(subjectRepo.getSubjectId(subjectId));

		return subjects;
	}

}

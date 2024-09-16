package com.tech.task.service;

import com.tech.task.bo.StudentBo;
import com.tech.task.entity.Student;

public interface StudentService {

	void create(Student student) throws Exception;

	void update(Student student, Long id) throws Exception;

	Student logIn(StudentBo studentBo) throws Exception;

	Student getByStudentId(Long id) throws Exception;

}

package com.tech.task.service;


import com.tech.task.entity.Subjects;

public interface SubjectService {

	void addSubject(Long studentId,Subjects subjects) throws Exception;

	Subjects getSubjectById(Long subjectId) throws Exception;
}

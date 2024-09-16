package com.tech.task.entity;

import java.util.ArrayList;
import java.util.List;

import com.tech.task.dto.StudentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "subjects")
public class Subjects {

	@Id
	@GeneratedValue
	@Column(name = "subject_id", length = 150)
	private Long subjectId;

	@Column(name = "subject_name", nullable = false, length = 150)
	private String subjectName;

	@Column(name = "marks", length = 100)
	private Double marks;

	@Transient
	private List<StudentDTO> studentDTOs = new ArrayList<>();

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Double getMarks() {
		return marks;
	}

	public void setMarks(Double marks) {
		this.marks = marks;
	}

	public List<StudentDTO> getStudentDTOs() {
		return studentDTOs;
	}

	public void setStudentDTOs(List<StudentDTO> studentDTOs) {
		this.studentDTOs = studentDTOs;
	}

}

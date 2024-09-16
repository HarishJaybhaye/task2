package com.tech.task.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_subject_mapping")
public class StudentSubjectMapping {

	@Id
	@GeneratedValue
	@Column(name = "student_subject_mapping_id")
	private Long StudentSubjectMappingId;

	@ManyToOne(targetEntity = Student.class)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne(targetEntity = Subjects.class)
	@JoinColumn(name = "subject_id", nullable = false)
	private Subjects subjects;

	public Long getStudentSubjectMappingId() {
		return StudentSubjectMappingId;
	}

	public void setStudentSubjectMappingId(Long studentSubjectMappingId) {
		StudentSubjectMappingId = studentSubjectMappingId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subjects getSubjects() {
		return subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}

}

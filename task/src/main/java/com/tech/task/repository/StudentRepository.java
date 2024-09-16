package com.tech.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tech.task.dto.SubjectDTO;
import com.tech.task.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByUserName(String userName);

	Student findByEmail(String email) throws Exception;

	@Query("SELECT su.subjectId AS subjectId, su.subjectName As subjectName, su.marks AS marks "
			+ "FROM Subjects su JOIN StudentSubjectMapping ssm ON ssm.subjects.subjectId = su.subjectId "
			+ "WHERE ssm.student.id = :studentId ORDER BY su.marks DESC")
	List<SubjectDTO> getStudentById(@Param("studentId") Long studentId) throws Exception;

}

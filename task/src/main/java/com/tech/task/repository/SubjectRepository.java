package com.tech.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tech.task.dto.StudentDTO;
import com.tech.task.entity.Subjects;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {

	@Query("SELECT \n"
			+ "    s.id AS id,\n"
			+ "    s.userName AS userName,\n"
			+ "    s.fullName AS fullName,\n"
			+ "    s.email AS email,\n"
			+ "    s.mobile AS mobile \n"
			+ "FROM \n"
			+ "    Student s \n"
			+ "JOIN \n"
			+ "    StudentSubjectMapping ssm ON ssm.student.id = s.id \n"
			+ "JOIN \n"
			+ "    Subjects su ON su.subjectId = ssm.subjects.subjectId \n"
			+ "WHERE \n"
			+ "    su.subjectId = :subjectId \n"
			+ "ORDER BY \n"
			+ "    ssm.subjects.marks DESC")
	List<StudentDTO> getSubjectId(@Param("subjectId") Long subjectId) throws Exception;
}

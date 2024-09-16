package com.tech.task.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.task.entity.StudentSubjectMapping;

public interface StudentSubjectMappingRepository extends JpaRepository<StudentSubjectMapping, Long>{

}

package com.edu.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.school.entity.StudentCourse;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

	Page<StudentCourse> findAllByCourseId(PageRequest pageRequest,Long id);

	Page<StudentCourse> findAllByStudentId(PageRequest pageRequest,Long id);
	
	@Query(value="SELECT * FROM STUDENT_COURSE"+
			" WHERE (:id IS NULL OR id = :id)"+
			" AND (:studentId IS NULL OR student_id = :studentId)"+
			" AND (:courseIdId IS NULL OR course_id = :courseId)",nativeQuery = true)
			Page<StudentCourse> searchSchoolByAnyField(PageRequest pageRequest,@Param("id") Long id,
					@Param("studentId") Long studentId,
					@Param("courseId") Long courseId);
	
}

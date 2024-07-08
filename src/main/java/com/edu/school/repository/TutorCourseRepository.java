package com.edu.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.school.entity.TutorCourse;

public interface TutorCourseRepository extends JpaRepository<TutorCourse, Long> {

	List<TutorCourse> findAllByCourseId(Long id);

	List<TutorCourse> findAllByTutorId(Long id);
	
	@Query(value="SELECT * FROM TUTOR_COURSE"+
			" WHERE (:id IS NULL OR id = :id)"+
			" AND (:tutorId IS NULL OR tutor_id = :tutorId)"+
			" AND (:courseIdId IS NULL OR course_id = :courseId)",nativeQuery = true)
			Page<TutorCourse> searchTutorByAnyField(PageRequest pageRequest,@Param("id") Long id,
					@Param("tutorId") Long tutorId,
					@Param("courseId") Long courseId);
}

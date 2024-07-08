package com.edu.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.school.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	
	
	@Query(value="SELECT * FROM COURSE"+
			" WHERE (:id IS NULL OR id = :id)"+
			" AND (:name IS NULL OR name = :name)"+
			" AND (:schoolId IS NULL OR school_id = :schoolId)"	,nativeQuery = true)
			Page<Course> searchCourseByAnyField(PageRequest pageRequest,@Param("id") Long id,
					@Param("name") String name,
					@Param("schoolId") Long schoolId);

}

package com.edu.school.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.TestRecord;


@Repository
public interface TestRecordRepository extends JpaRepository<TestRecord, Long> {

//	List<TestRecord> findByStudentIdAndCourseId(Long studentId, Long courseId);
//	List<Integer> findMarkByStudentIdAndCourseId(Long studentId, Long courseId);

	List<TestRecord> findByStudentIdAndCourseId(Long studentId, Long courseId);

	List<TestRecord> findAllByStudentId(Long studentId);

	List<TestRecord> findAllByCourseId(Long courseId);
	
	@Query(value="SELECT * FROM TestRecord"+
			" WHERE (:id IS NULL OR id = :id)"+
			" AND (:studentId IS NULL OR student_id = :studentId)"+
			" AND (:courseIdId IS NULL OR course_id = :courseId)",nativeQuery = true)
			Page<TestRecord> searchTestRecordByAnyField(PageRequest pageRequest,@Param("id") Long id,
					@Param("studentId") Long studentId,
					@Param("courseId") Long courseId); 
	
	
}

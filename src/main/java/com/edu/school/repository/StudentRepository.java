package com.edu.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	Page<Student> findAllBySchoolId(PageRequest pageRequest,final long schoolId);
	
	@Query(value="SELECT * FROM STUDENT"+
	" WHERE (:id IS NULL OR id = :id)"+
	" AND (:name IS NULL OR name = :name)"+
	" AND (:email IS NULL OR email = :email)"+
	" AND (:address IS NULL OR address = :address)"+
	" AND (:schoolId IS NULL OR school_id = :schoolId)"	,nativeQuery = true)
	Page<Student> searchStudentByAnyField(@Param("id") Long id,
			@Param("name") String name,
			@Param("email") String email,
			@Param("address") String address,
			@Param("schoolId") Long schoolId,PageRequest pageRequest);

	Student findByEmail(String userEmail);

	boolean existsByEmail(String email);
}

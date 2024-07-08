package com.edu.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	
	@Query(value="SELECT * FROM SCHOOL"+
			" WHERE (:id IS NULL OR id = :id)"+
			" AND (:name IS NULL OR name = :name)"+
			" AND (:address IS NULL OR address = :address)",nativeQuery = true)
			Page<School> searchSchoolByAnyField(PageRequest pageRequest,@Param("id") Long id,
					@Param("name") String name,
					@Param("address") String address);
	
	

}

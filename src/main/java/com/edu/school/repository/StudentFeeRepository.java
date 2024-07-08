package com.edu.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.StudentFee;


@Repository
public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {

}

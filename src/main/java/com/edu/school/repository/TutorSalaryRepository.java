package com.edu.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.TutorSalary;

@Repository
public interface TutorSalaryRepository extends JpaRepository<TutorSalary, Long> {

}

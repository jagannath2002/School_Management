package com.edu.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Page<Question> findAllByCourseId(PageRequest pageRequest,Long courseId);

}

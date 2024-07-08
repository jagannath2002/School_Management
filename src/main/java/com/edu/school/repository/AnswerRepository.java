package com.edu.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.school.entity.Answer;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>  {

}

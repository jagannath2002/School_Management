package com.edu.school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.school.DTO.QuestionDTO;
import com.edu.school.entity.Question;
import com.edu.school.service.QuestionService;


@RestController
@RequestMapping("api/v1/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/create")
	public Question CreateQuestion(@RequestBody Question question) {
		return this.questionService.CreateQuestion(question);
	}
	@GetMapping("/read/{id}")
	public Question RetriveQuestion(@PathVariable Long id) {
		return this.questionService.RetriveQuestion(id);
	}
	@GetMapping("/all")
	public List<Question> RetriveQuestionAll() {
		return this.questionService.RetriveQuestionAll();
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> DeleteQuestion(@PathVariable Long id){
		return this.questionService.DeleteQuestion(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateQuestion(@PathVariable Long id,@RequestBody Question question) {
		return this.questionService.updateQuestion(id, question);
	}
	@GetMapping("/testquestion")
	public Page<QuestionDTO> testQuestions(
			@RequestParam Long courseId,
			@RequestParam Integer pageNo,
			@RequestParam Integer pageSize,
			@RequestParam(defaultValue="id")String fieldName,
			@RequestParam(defaultValue="ASC")Direction direction)
	{
		return this.questionService.testQuestions(courseId,pageNo,pageSize,fieldName, direction);
	}
	
	

}

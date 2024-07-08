package com.edu.school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.school.entity.Answer;
import com.edu.school.service.AnswerService;


@RestController
@RequestMapping("api/v1/answer")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@PostMapping("/create")
	public Answer createAnswer(@RequestBody Answer answer) {
		return this.answerService.CreateAnswer(answer);
	}
	@GetMapping("/read/{id}")
	public Answer retriveAnswer(@PathVariable Long id) {
		return this.answerService.RetriveAnswer(id);
	}
	@GetMapping("/all")
	public List<Answer> retriveAnswer() {
		return this.answerService.RetriveAnswerAll();
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteAnswer(@PathVariable Long id){
		return this.answerService.DeleteAnswer(id);
	}
	@PutMapping("/update/{id}") 
	public Map<String,String> updateAnswer(@PathVariable Long id,@RequestBody Answer Answer){
		return this.answerService.updateAnswer(id, Answer);
	}
	
}

package com.edu.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.school.entity.Answer;
import com.edu.school.repository.AnswerRepository;


@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	public Answer CreateAnswer(Answer answer) {
		return this.answerRepository.save(answer);
	}
	public Answer RetriveAnswer(Long id) {
		return this.answerRepository.findById(id).orElseThrow();
	}
	public List<Answer> RetriveAnswerAll() {
		return this.answerRepository.findAll();
	}
	public Map<String,String> DeleteAnswer(Long id){
		answerRepository.deleteById(id);
		Map<String,String> message=new HashMap<>();
		message.put("Message", "Successfully Deleted");
		return message; 
	}
	public Map<String, String> updateAnswer(Long id,Answer AnswerRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Answer>requestMessage=answerRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final Answer answerResponse=requestMessage.get();
			if(AnswerRequest.getAnswer()!= null) {
				answerResponse.setAnswer(AnswerRequest.getAnswer());
			}
			if(AnswerRequest.getQuestion()!=null) {
				answerResponse.setQuestion(AnswerRequest.getQuestion());
			}
			if(AnswerRequest.getStudent()!=null) {
				answerResponse.setStudent(AnswerRequest.getStudent());
			}
			if(AnswerRequest.getCourse()!=null) {
				answerResponse.setCourse(AnswerRequest.getCourse());
			}
			answerRepository.save(answerResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;
				
	}
}

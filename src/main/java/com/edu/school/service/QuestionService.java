package com.edu.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edu.school.DTO.QuestionDTO;
import com.edu.school.entity.Question;
import com.edu.school.repository.QuestionRepository;


@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public Question CreateQuestion(Question question) {
		return this.questionRepository.save(question);
	}
	public Question RetriveQuestion(Long id) {
		return this.questionRepository.findById(id).orElseThrow();
	}
	public List<Question> RetriveQuestionAll() {
		return this.questionRepository.findAll();
	}
	public Map<String,String> DeleteQuestion(Long id){
		questionRepository.deleteById(id);
		Map<String,String> message=new HashMap<>();
		message.put("Message", "Successfully Deleted");
		return message; 
	}
	public Map<String, String> updateQuestion(Long id,Question questionRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Question>requestMessage=questionRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final Question questionResponse=requestMessage.get();
			if(questionRequest.getQuestion()!=null) {
				questionResponse.setQuestion(questionRequest.getQuestion());
			}
			if(questionRequest.getOptionOne()!= null) {
				questionResponse.setOptionOne(questionRequest.getOptionOne());
			}
			if(questionRequest.getOptionTwo()!=null) {
				questionResponse.setOptionTwo(questionRequest.getOptionTwo());
			}
			if(questionRequest.getOptionThree()!=null) {
				questionResponse.setOptionThree(questionRequest.getOptionThree());
			}
			if(questionRequest.getCourse()!=null) {
				questionResponse.setCourse(questionRequest.getCourse());
			}
			if(questionRequest.getAnswer()!=null) {
				questionResponse.setAnswer(questionRequest.getAnswer());
			}
			questionRepository.save(questionResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;		
	}
	
//	public List <QuestionDTO> testQuestion(Long courseId){
//		 List <QuestionDTO> displayQuestion=new LinkedList<>();
//		 List<Question> listofQuestion=questionRepository.findAllByCourseId(courseId);
//		
//		Iterator<Question> singleQuestion=listofQuestion.iterator();
//		while(singleQuestion.hasNext()) {
//			Question questionObject=singleQuestion.next();
//			QuestionDTO questionDTO=new QuestionDTO();
//	     	questionDTO.setQuestion(questionObject.getQuestion());
//	    	questionDTO.setOptionOne(questionObject.getOptionOne());
//	     	questionDTO.setOptionTwo(questionObject.getOptionTwo());
//			questionDTO.setOptionThree(questionObject.getOptionThree());
//			displayQuestion.add(convertDTO(singleQuestion.next()));		
//			}
//		//List<QuestionDTO> newQuestion=listofQuestion.map(this::convertDTO);
//		return displayQuestion;
//	}
	
	public Page<QuestionDTO> testQuestions(Long courseId,Integer pageNo,Integer pageSize,String fieldName,Sort.Direction direction) {
		Page<Question> question=questionRepository.findAllByCourseId(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),courseId);
		Page<QuestionDTO> newQuestion=question.map(this::convertDTO);
		return newQuestion;
	}
	public QuestionDTO convertDTO(Question question) {
		QuestionDTO dto=new QuestionDTO();
		dto.setQuestion(question.getQuestion());
		dto.setOptionOne(question.getOptionOne());
		dto.setOptionTwo(question.getOptionTwo());
		dto.setOptionThree(question.getOptionThree());
		return dto;
	}
}

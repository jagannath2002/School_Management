package com.edu.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.school.entity.TutorSalary;
import com.edu.school.repository.TutorSalaryRepository;

@Service
public class TutorSalaryService {
	@Autowired
	private TutorSalaryRepository tutorSalaryRepository;
	
	public TutorSalary createStudentFee(TutorSalary tutorSalary) {
		tutorSalaryRepository.save(tutorSalary);
		return this.tutorSalaryRepository.findById(tutorSalary.getId()).orElseThrow();
	}
	public TutorSalary retriveStudentFee(Long id) {
		return this.tutorSalaryRepository.findById(id).orElseThrow();
	}
	public List<TutorSalary> retriveStudentFeeAll(){
		return this.tutorSalaryRepository.findAll();
	}
	public Map<String,String> deleteStudentFee(Long id){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<TutorSalary>requestMessage=tutorSalaryRepository.findById(id);
		if(requestMessage.isPresent()) {
			tutorSalaryRepository.deleteById(id);
			responceMessage.put("message", "Deleted Successfully");
		}
		else {
			responceMessage.put("message", " ID not Found");
			
		}
		return responceMessage;
	}
	public Map<String, String> updateStudentFee(Long id,TutorSalary tutorSalaryRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<TutorSalary>requestMessage=tutorSalaryRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final TutorSalary tutorSalaryResponse=requestMessage.get();
			if(tutorSalaryRequest.getSalary()!=null) {
				tutorSalaryResponse.setSalary(tutorSalaryRequest.getSalary());
			}
			if(tutorSalaryRequest.getTutor()!=null) {
				tutorSalaryResponse.setTutor(tutorSalaryRequest.getTutor());
			}
			tutorSalaryRepository.save(tutorSalaryResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;
				
	}
}

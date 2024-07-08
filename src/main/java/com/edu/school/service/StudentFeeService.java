package com.edu.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.school.entity.StudentFee;
import com.edu.school.repository.StudentFeeRepository;

@Service
public class StudentFeeService {
	
	@Autowired
	private StudentFeeRepository studentFeeRepository;
	
	public StudentFee createStudentFee(StudentFee studentFee) {
		studentFeeRepository.save(studentFee);
		return this.studentFeeRepository.findById(studentFee.getId()).orElseThrow();
	}
	public StudentFee retriveStudentFee(Long id) {
		return this.studentFeeRepository.findById(id).orElseThrow();
	}
	public List<StudentFee> retriveStudentFeeAll(){
		return this.studentFeeRepository.findAll();
	}
	public Map<String,String> deleteStudentFee(Long id){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<StudentFee>requestMessage=studentFeeRepository.findById(id);
		if(requestMessage.isPresent()) {
			studentFeeRepository.deleteById(id);
			responceMessage.put("message", "Deleted Successfully");
		}
		else {
			responceMessage.put("message", " ID not Found");
			
		}
		return responceMessage;
	}
	public Map<String, String> updateStudentFee(Long id,StudentFee studentFeeRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<StudentFee>requestMessage=studentFeeRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final StudentFee studentFeeResponse=requestMessage.get();
			if(studentFeeRequest.getFee()!=null) {
				studentFeeResponse.setFee(studentFeeRequest.getFee());
			}
			if(studentFeeRequest.getStudent()!=null) {
				studentFeeResponse.setStudent(studentFeeRequest.getStudent());
			}
			studentFeeRepository.save(studentFeeResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;
				
	}
}

package com.edu.school.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edu.school.entity.Tutor;
import com.edu.school.repository.TutorRepository;

@Service
public class TutorService {
	
	@Autowired
	private TutorRepository tutorRepository;
	
	public Tutor createTutor(Tutor tutor) {
		tutorRepository.save(tutor);
		return this.tutorRepository.findById(tutor.getId()).orElseThrow();
	}
	public Tutor retriveTutor(Long id) {
		return this.tutorRepository.findById(id).orElseThrow();
	}
	public Page<Tutor> retriveTutorAll(Integer pageNo,Integer pageSize,String fieldName,Direction direction){
		return this.tutorRepository.findAll(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)));
	}
	public Map<String,String> deleteTutor(Long id){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Tutor>requestMessage=tutorRepository.findById(id);
		if(requestMessage.isPresent()) {
			tutorRepository.deleteById(id);
			responceMessage.put("message", "Deleted Successfully");
		}
		else {
			responceMessage.put("message", " ID not Found");
			
		}
		return responceMessage;
	}
	public Map<String, String> updateTutor(Long id,Tutor TutorRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Tutor>requestMessage=tutorRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final Tutor TutorResponse=requestMessage.get();
			if(TutorRequest.getName()!=null) {
				TutorResponse.setName(TutorRequest.getName());
			}
			if(TutorRequest.getAddress()!=null) {
				TutorResponse.setAddress(TutorRequest.getAddress());
			}
			if(TutorRequest.getSchool()!=null) {
				TutorResponse.setSchool(TutorRequest.getSchool());
			}
			tutorRepository.save(TutorResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;
				
	}
	public Page<Tutor> retriveTuterBySchoolId(Long id,Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		
		return this.tutorRepository.findAllBySchoolId(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id);
	}
	public Tutor searchTutorByEmail(String userEmail) {
		return this.tutorRepository.findByEmail(userEmail);
	}
	
	public Page<Tutor> searchTutor(Long id,String name,String email,String address,Long schoolId,Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		Page<Tutor> student=tutorRepository.searchTutorByAnyField(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id,name,email,address,schoolId);
		return student;
	}



}

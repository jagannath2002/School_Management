package com.edu.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edu.school.entity.TutorCourse;
import com.edu.school.repository.TutorCourseRepository;

@Service
public class TutorCourseService {
	
	@Autowired
	private TutorCourseRepository tutorCourseRepository;
	
	public TutorCourse createTutorCourse(TutorCourse tutorCourse) {
		tutorCourseRepository.save(tutorCourse);
		return this.tutorCourseRepository.findById(tutorCourse.getId()).orElseThrow();
	}
	public TutorCourse retriveTutorCourse(Long id) {
		return this.tutorCourseRepository.findById(id).orElseThrow();
	}
	public List<TutorCourse> retriveTutorCourseAll(){
		return this.tutorCourseRepository.findAll();
	}
	public Map<String,String> deleteTutorCourse(Long id){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<TutorCourse>requestMessage=tutorCourseRepository.findById(id);
		if(requestMessage.isPresent()) {
			tutorCourseRepository.deleteById(id);
			responceMessage.put("message", "Deleted Successfully");
		}
		else {
			responceMessage.put("message", " ID not Found");
			
		}
		return responceMessage;
	}
	public Map<String, String> updateTutorCourse(Long id,TutorCourse tutorCourseRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<TutorCourse>requestMessage=tutorCourseRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final TutorCourse tutorCourseResponse=requestMessage.get();
			if(tutorCourseRequest.getTutor()!=null) {
				tutorCourseResponse.setTutor(tutorCourseRequest.getTutor());
			}
			if(tutorCourseRequest.getCourse()!=null) {
				tutorCourseResponse.setCourse(tutorCourseRequest.getCourse());
			}
			tutorCourseRepository.save(tutorCourseResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;
				
	}
	public List<TutorCourse> retriveAllTutorByCourseId(Long id) {
		
		return this.tutorCourseRepository.findAllByCourseId(id);
	}
	public List<TutorCourse> retriveTutorCourseByTutorId(Long id) {
	
		return this.tutorCourseRepository.findAllByTutorId(id);
	}
	public Page<TutorCourse>searchTutorCourse(Long id, Long tutorId, Long courseId,Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		Page<TutorCourse> tutorCourse=tutorCourseRepository.searchTutorByAnyField(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id,tutorId,courseId);
		return tutorCourse;
	}
}

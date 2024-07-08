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

import com.edu.school.entity.Course;
import com.edu.school.repository.CourseRepository;

@Service
public class CourseService {
	
	
	@Autowired
	private CourseRepository courseRepository;
	
	public Course createCourse(Course course) {
		courseRepository.save(course);
		return this.courseRepository.findById(course.getId()).orElseThrow();
	}
	public Course retriveCourse(Long id) {
		return this.courseRepository.findById(id).orElseThrow();
	}
	public List<Course> retriveCourseAll(){
		return this.courseRepository.findAll();
	}
	public Map<String,String> deleteCourse(Long id){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Course>requestMessage=courseRepository.findById(id);
		if(requestMessage.isPresent()) {
			courseRepository.deleteById(id);
			responceMessage.put("message", "Deleted Successfully");
		}
		else {
			responceMessage.put("message", " ID not Found");
			
		}
		return responceMessage;
	}
	public Map<String, String> updateCourse(Long id,Course courseRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Course>requestMessage=courseRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final Course courseResponse=requestMessage.get();
			if(courseRequest.getName()!=null) {
				courseResponse.setName(courseRequest.getName());
			}
			if(courseRequest.getSchool()!=null) {
				courseResponse.setSchool(courseRequest.getSchool());
			}
			
			courseRepository.save(courseResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;
				
	}
	public Page<Course> searchCourse(Long id, String name, Long schoolId, Integer pageNo, Integer pageSize,
			String fieldName, Direction direction) {
		Page<Course>course=courseRepository.searchCourseByAnyField(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id,name,schoolId);
		return course;
	}
	

}

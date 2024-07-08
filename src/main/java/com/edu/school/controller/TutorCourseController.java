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

import com.edu.school.entity.TutorCourse;
import com.edu.school.service.TutorCourseService;


@RestController
@RequestMapping("/api/v1/tutorcourse")
public class TutorCourseController {
	
	@Autowired
	private TutorCourseService tutorCourseService;
	
	@PostMapping("/create")
	public TutorCourse createTutorCourse(@RequestBody TutorCourse tutorCourse) {
		return this.tutorCourseService.createTutorCourse(tutorCourse);
	}
	@GetMapping("/read/{id}")
	public TutorCourse retriveTutorCourse(@PathVariable Long id) {
		return this.tutorCourseService.retriveTutorCourse(id);
	}
	@GetMapping("/all")
	public List<TutorCourse> retriveTutorCourseAll() {
		return this.tutorCourseService.retriveTutorCourseAll();
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteTutorCourse(@PathVariable Long id){
		return this.tutorCourseService.deleteTutorCourse(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateTutorCourse(@PathVariable Long id,@RequestBody TutorCourse tutorCourse) {
		return this.tutorCourseService.updateTutorCourse(id, tutorCourse);
	}
	@GetMapping("/course/{id}")
	public List<TutorCourse> retriveAllTutorByCourseId(@PathVariable Long id) {
		return this.tutorCourseService.retriveAllTutorByCourseId(id);
	}
	@GetMapping("/tutor/{id}")
	public List<TutorCourse> retriveTutorCoursesByTutorId(@PathVariable Long id){
		return this.tutorCourseService.retriveTutorCourseByTutorId(id);
	}
	@GetMapping("/search/")
	public Page<TutorCourse> SearchStudentCourse(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long tutorId,
			@RequestParam(required = false) Long courseId,
	        @RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction)
	{ 
		return this.tutorCourseService.searchTutorCourse(id,tutorId,courseId,pageNo,pageSize,fieldName,direction);
	}

}

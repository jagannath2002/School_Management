package com.edu.school.controller;

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

import com.edu.school.entity.Tutor;
import com.edu.school.service.TutorService;

@RestController
@RequestMapping("/api/v1/tutor")
public class TutorController {
	@Autowired
	private TutorService tutorService;
	@PostMapping("/create")
	public Tutor createTutor(@RequestBody Tutor tutor) {
		return this.tutorService.createTutor(tutor);
	}
	@GetMapping("/read/{id}")
	public Tutor retriveTutor(@PathVariable Long id) {
		return this.tutorService.retriveTutor(id);
	}
	@GetMapping("/all")
	public Page<Tutor> retriveTutorAll(
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction
			) {
		return this.tutorService.retriveTutorAll(pageNo, pageSize, fieldName, direction);
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteTutor(@PathVariable Long id){
		return this.tutorService.deleteTutor(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateTutor(@PathVariable Long id,@RequestBody Tutor tutor) {
		return this.tutorService.updateTutor(id, tutor);
	}
	@GetMapping("/school/{id}")
	public Page<Tutor> retriveTuterBySchoolId(@PathVariable Long id,
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction
			) {
		return this.tutorService.retriveTuterBySchoolId(id,pageNo, pageSize, fieldName,direction);
	}
	@GetMapping("/search")
	public Page<Tutor> SearchStudent(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String address,
			@RequestParam(required = false) Long schoolId,
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction)
	{
		return this.tutorService.searchTutor(id,name,email,address,schoolId,pageNo, pageSize, fieldName, direction);
	}
}

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

import com.edu.school.entity.School;
import com.edu.school.service.SchoolService;

@RestController  
@RequestMapping("api/v1/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	@GetMapping("/read/{id}")
	public School getSchool(@PathVariable Long id) {
		return this.schoolService.getSchool(id);
	}
	@GetMapping("/all")
	public Page<School>Schools(
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction
			) {
		return this.schoolService.retriveSchools(pageNo,pageSize,fieldName,direction);
	}
	@PostMapping("/create")
	public School createSchool(@RequestBody School school){	
		return this.schoolService.createSchool(school);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateSchool(@PathVariable Long id,@RequestBody School school) {
		return this.schoolService.updateSchool(id, school);
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteSchool(@PathVariable Long id){
		return this.schoolService.deleteSchool(id);
	}
	@GetMapping("/search/")
	public Page<School> SearchSchool(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String address,
	        @RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction)
	{ 
		return this.schoolService.searchSchool(id,name,address,pageNo,pageSize,fieldName,direction);
	}
}

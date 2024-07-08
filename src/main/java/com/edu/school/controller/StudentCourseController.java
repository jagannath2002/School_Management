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

import com.edu.school.entity.StudentCourse;
import com.edu.school.service.StudentCourseService;


@RestController
@RequestMapping("/api/v1/studentcourse")
public class StudentCourseController {
	@Autowired
	private StudentCourseService studentCourseService;
	
	@PostMapping("/create")
	public StudentCourse createStudentCourse(@RequestBody StudentCourse studentCourse) {
		return this.studentCourseService.createStudentCourse(studentCourse);
	}
	@GetMapping("/read/{id}")
	public StudentCourse retriveStudentCourse(@PathVariable Long id) {
		return this.studentCourseService.retriveStudentCourse(id);
	}
	@GetMapping("/all")
	public List<StudentCourse> retriveStudentCourseAll() {
		return this.studentCourseService.retriveStudentCourseAll();
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteStudentCourse(@PathVariable Long id){
		return this.studentCourseService.deleteStudentCourse(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateStudentCourse(@PathVariable Long id,@RequestBody StudentCourse studentCourse) {
		return this.studentCourseService.updateStudentCourse(id, studentCourse);
	}
	@GetMapping("/course/{id}")
	public Page<StudentCourse> retriveStudentByCourseId(@PathVariable Long id,
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction
			){
		return this.studentCourseService. retriveStudentByCourseId(id, pageNo, pageSize, fieldName, direction);
	}
	@GetMapping("/student/{id}")
	public Page<StudentCourse> retriveStudentByStudentId(@PathVariable Long id,
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction
			){
		return this.studentCourseService. retriveStudentByStudentId(id,pageNo, pageSize, fieldName, direction);
	}
	@GetMapping("/search/")
	public Page<StudentCourse> SearchStudentCourse(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long studentId,
			@RequestParam(required = false) Long courseId,
	        @RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction)
	{ 
		return this.studentCourseService.searchStudentCourse(id,studentId,courseId,pageNo,pageSize,fieldName,direction);
	}
	
}

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

import com.edu.school.entity.Student;
import com.edu.school.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		return this.studentService.createStudent(student);
	}
	@GetMapping("/read/{id}")
	public Student retriveStudent(@PathVariable Long id) {
		return this.studentService.retriveStudent(id);
	}
	@GetMapping("/all")
	public Page<Student> retriveStudent(
			@RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction
			) {
		return this.studentService.retriveStudentAll(pageNo, pageSize, fieldName,direction);
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteStudent(@PathVariable Long id){
		return this.studentService.deleteStudent(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateStudent(@PathVariable Long id,@RequestBody Student student) {
		return this.studentService.updateStudent(id, student);
	}
	@GetMapping("/search")
	public Page<Student> SearchStudent(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String address,
			@RequestParam(required = false) Long schoolId,
			@RequestParam(required = false,defaultValue="0") Integer pageNo,
	        @RequestParam(required = false,defaultValue="2") Integer pageSize,
	        @RequestParam(required = false,defaultValue="id")String fieldName,
	        @RequestParam(required = false,defaultValue="ASC") Direction direction)
	{
		return this.studentService.searchStudent(id,name,email,address,schoolId,pageNo, pageSize, fieldName, direction);
	}
	
	
}

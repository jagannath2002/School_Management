package com.edu.school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.school.entity.StudentFee;
import com.edu.school.service.StudentFeeService;


@RestController
@RequestMapping("/api/v1/studentfee")
public class StudentFeeController {
	@Autowired
	private StudentFeeService studentFeeService;
	
	@PostMapping("/create")
	public StudentFee createStudentFee(@RequestBody StudentFee studentFee) {
		return this.studentFeeService.createStudentFee(studentFee);
	}
	@GetMapping("/read/{id}")
	public StudentFee retriveStudentFee(@PathVariable Long id) {
		return this.studentFeeService.retriveStudentFee(id);
	}
	@GetMapping("/all")
	public List<StudentFee> retriveStudentFeeAll() {
		return this.studentFeeService.retriveStudentFeeAll();
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteStudentFee(@PathVariable Long id){
		return this.studentFeeService.deleteStudentFee(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateStudentFee(@PathVariable Long id,@RequestBody StudentFee studentFee) {
		return this.studentFeeService.updateStudentFee(id, studentFee);
	}

}

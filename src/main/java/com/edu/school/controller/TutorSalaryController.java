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

import com.edu.school.entity.TutorSalary;
import com.edu.school.service.TutorSalaryService;


@RestController
@RequestMapping("api/v1/tutorsalary")
public class TutorSalaryController {
	@Autowired
	private TutorSalaryService tutorSalaryService;
	
	@PostMapping("/create")
	public TutorSalary createStudentFee(@RequestBody TutorSalary tutorSalary) {
		return this.tutorSalaryService.createStudentFee(tutorSalary);
	}
	@GetMapping("/read/{id}")
	public TutorSalary retriveStudentFee(@PathVariable Long id) {
		return this.tutorSalaryService.retriveStudentFee(id);
	}
	@GetMapping("/all")
	public List<TutorSalary> retriveStudentFeeAll() {
		return this.tutorSalaryService.retriveStudentFeeAll();
	}
	@DeleteMapping("/delete/{id}")
	public Map<String,String> deleteStudentFee(@PathVariable Long id){
		return this.tutorSalaryService.deleteStudentFee(id);
	}
	@PutMapping("/update/{id}")
	public Map<String,String> updateStudentFee(@PathVariable Long id,@RequestBody TutorSalary tutorSalary) {
		return this.tutorSalaryService.updateStudentFee(id, tutorSalary);
	}
}

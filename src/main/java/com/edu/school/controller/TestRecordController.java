package com.edu.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.school.DTO.TestRecordByStudentDTO;
import com.edu.school.entity.TestRecord;
import com.edu.school.service.TestRecordService;


@RestController
@RequestMapping("api/v1/testRecord")
public class TestRecordController {

	@Autowired
	private TestRecordService testRecordService;
	
	@PostMapping("/create")
	public TestRecord createAnswer(@RequestBody TestRecord testRecord) {
		return this.testRecordService.createTestRecord(testRecord);
	}
	@GetMapping("/read")
	public TestRecord retriveAnswer(@RequestParam Long studentId,@RequestParam Long courseId) {
		return this.testRecordService.retriveTestRecordByStudentIdAndCourseId(studentId,courseId);
	}
	@GetMapping("/course/{courseId}")
	public List<TestRecord> TestRecordBySubjectId(@PathVariable Long courseId) {
		return this.testRecordService.TestRecordBysubjectId(courseId);
	}
	@PutMapping("/update")
	public String CalculateTestRecordByMatchingAnswerAndQuestion() {
		return this.testRecordService.calculateTestRecordByMatchingAnswerAndQuestion();
	}
	@GetMapping("/{studentId}")
	public List<TestRecordByStudentDTO> TestRecordByStudentId(@PathVariable Long studentId) {
		return this.testRecordService.TestRecordByStudent(studentId);
	}
	@GetMapping("/search/")
	public Page<TestRecord> SearchTestRecord(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long studentId,
			@RequestParam(required = false) Long courseId,
	        @RequestParam(defaultValue="0") Integer pageNo,
	        @RequestParam(defaultValue="2") Integer pageSize,
	        @RequestParam(defaultValue="id")String fieldName,
	        @RequestParam(defaultValue="ASC")Direction direction)
	{
		return this.testRecordService.searchTestRecord(id,studentId,courseId,pageNo,pageSize,fieldName,direction);
	}
	
	
}

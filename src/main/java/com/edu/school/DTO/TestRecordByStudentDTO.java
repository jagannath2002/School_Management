package com.edu.school.DTO;

import lombok.Data;

@Data
public class TestRecordByStudentDTO {
	private Long id;
	private String SchoolName;
	private Long StudentId;
	private String StudentName;
	private String courseName;
	private Integer mark;
}

package com.edu.school.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edu.school.entity.Student;
import com.edu.school.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student createStudent(Student student) {
		studentRepository.save(student);
		return this.studentRepository.findById(student.getId()).orElseThrow();
	}
	public Student retriveStudent(Long id) {
		return this.studentRepository.findById(id).orElseThrow();
	}
	public Page<Student> retriveStudentAll(Integer pageNo,Integer pageSize,String fieldName,Direction direction){
		Page<Student> studentBySchoolId=studentRepository.findAll(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)));
		return studentBySchoolId;
	}
	public Map<String,String> deleteStudent(Long id){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Student>requestMessage=studentRepository.findById(id);
		if(requestMessage.isPresent()) {
			studentRepository.deleteById(id);
			responceMessage.put("message", "Deleted Successfully");
		}
		else {
			responceMessage.put("message", " ID not Found");
			
		}
		return responceMessage;
	}
	public Map<String, String> updateStudent(Long id,Student studentRequest){
		Map<String,String>responceMessage=new HashMap<>();
		Optional<Student>requestMessage=studentRepository.findById(id);
		if(requestMessage.isEmpty()) {
			responceMessage.put("message", "ID not Found");
		}
		else {
			final Student studentResponse=requestMessage.get();
			if(studentRequest.getName()!=null) {
				studentResponse.setName(studentRequest.getName());
			}
			if(studentRequest.getAge()!= 0) {
				studentResponse.setAge(studentRequest.getAge());
			}
			if(studentRequest.getAddress()!=null) {
				studentResponse.setAddress(studentRequest.getAddress());
			}
			if(studentRequest.getSchool()!=null) {
				studentResponse.setSchool(studentRequest.getSchool());
			}
			studentRepository.save(studentResponse);
			responceMessage.put("message", "Updated Successfully");
		}
		return responceMessage;		
	}
	public Page<Student> RetriveStudentBySchoolId(Long id,Integer pageNo,Integer pageSize,String fieldName,Direction direction){
		Page<Student> studentBySchoolId=studentRepository.findAllBySchoolId(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id);
		return studentBySchoolId;
	}
	public Page<Student> searchStudent(Long id,String name,String email,String address,Long schoolId,Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		Page<Student> student=studentRepository.searchStudentByAnyField(id,name,email,address,schoolId,PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)));
		return student;
	}
	public Student searchStudentByEmail(String userEmail) {
		return this.studentRepository.findByEmail(userEmail);
	}
	
	
}

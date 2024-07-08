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

import com.edu.school.entity.School;
import com.edu.school.repository.SchoolRepository;

@Service
public class SchoolService {
	@Autowired
	private SchoolRepository schoolRepository;
	
	public School createSchool(School school) {
		return this.schoolRepository.save(school);
		
	}
	public School getSchool(Long id) {
		return this.schoolRepository.findById(id).orElseThrow();
		
	}
	public Page<School> retriveSchools(Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		return this.schoolRepository.findAll(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)));
		
	}
	public Map<String,String> deleteSchool(Long id){
		schoolRepository.deleteById(id);
		Map<String,String> message=new HashMap<>();
		message.put("Message", "Successfully Deleted");
		return message;
	}
	public Map<String,String> updateSchool(Long id,School schoolRequest) {
		
		final Map<String,String> responseMap=new HashMap<>();
		final Optional<School>school=schoolRepository.findById(id);
		
		if(school.isEmpty()) responseMap.put("message", "Id not found");
		else {
			final School schoolResponse=school.get();
			if(schoolRequest.getName()!=null)
				schoolResponse.setName(schoolRequest.getName());
			if(schoolRequest.getAddress()!=null)
				schoolResponse.setAddress(schoolRequest.getAddress());
			this.schoolRepository.save(schoolResponse);
			responseMap.put("message", "Successfully updated");
		}

		return responseMap ;
	}
	public Page<School> searchSchool(Long id, String name, String address,Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		Page<School> school=schoolRepository.searchSchoolByAnyField(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id,name,address);
		return school;
	}
	
}

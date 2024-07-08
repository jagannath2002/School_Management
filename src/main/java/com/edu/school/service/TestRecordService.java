package com.edu.school.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edu.school.DTO.TestRecordByStudentDTO;
import com.edu.school.entity.Answer;
import com.edu.school.entity.Question;
import com.edu.school.entity.TestRecord;
import com.edu.school.repository.AnswerRepository;
import com.edu.school.repository.QuestionRepository;
import com.edu.school.repository.TestRecordRepository;


@Service
public class TestRecordService {
	
	@Autowired
    private TestRecordRepository testRecordRepository;
	
	public TestRecord createTestRecord(TestRecord TestRecord) {
		testRecordRepository.save(TestRecord);
		    
		return this.testRecordRepository.findById(TestRecord.getId()).orElseThrow();
	}
	
	public TestRecord retriveTestRecordByStudentIdAndCourseId(Long studentId, Long courseId) {
		try {
			return this.testRecordRepository.findByStudentIdAndCourseId(studentId,courseId).stream().findFirst().orElseThrow(()-> new BadRequestException("Not found"));
		} catch (BadRequestException e) {
           //e.printStackTrace();
		}
		return null;
	}
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	public String calculateTestRecordByMatchingAnswerAndQuestion() {
        List<Answer> answerTable = answerRepository.findAll();
        Map<Long, Question> questionMap = questionRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Question::getId, q -> q));
        
        final List<TestRecord> testRecordList = new LinkedList<>();
        for (Answer answer : answerTable) {
            Question correspondingQuestion = questionMap.get(answer.getQuestion().getId());
            if (correspondingQuestion != null && answer.getAnswer().equals(correspondingQuestion.getAnswer())) {
                TestRecord studentRecord = retriveTestRecordByStudentIdAndCourseId(answer.getStudent().getId(), answer.getCourse().getId());

                if (studentRecord != null) {
                    studentRecord.setMark(studentRecord.getMark() + 1);
                    testRecordList.add(studentRecord);
                } else {
                    TestRecord newStudentRecord = new TestRecord();
                    newStudentRecord.setMark(1);
                    newStudentRecord.setStudent(answer.getStudent());
                    newStudentRecord.setCourse(answer.getCourse());
                    testRecordList.add(studentRecord);
                }     
            }
            this.testRecordRepository.saveAll(testRecordList);    
        }  
        return "Record Updated";
    }
	public List<TestRecordByStudentDTO> TestRecordByStudent(Long studentId){
		
		List<TestRecord>StudentByAllCourse=testRecordRepository.findAllByStudentId(studentId);
		List<TestRecordByStudentDTO>displayRecord=new LinkedList<>();
		for(int i=0;i<StudentByAllCourse.size();i++) {
			TestRecord studentSingleCourse=StudentByAllCourse.get(i);
			TestRecordByStudentDTO studentSingleCourseDTO=new TestRecordByStudentDTO();
			studentSingleCourseDTO.setId(studentSingleCourse.getId());
			studentSingleCourseDTO.setSchoolName(studentSingleCourse.getStudent().getSchool().getName());
			studentSingleCourseDTO.setStudentId(studentSingleCourse.getStudent().getId());
			studentSingleCourseDTO.setCourseName(studentSingleCourse.getCourse().getName());
			studentSingleCourseDTO.setStudentName(studentSingleCourse.getStudent().getName());
			studentSingleCourseDTO.setMark(studentSingleCourse.getMark());
			displayRecord.add(studentSingleCourseDTO);
		}
		return displayRecord;
	}

	public List<TestRecord> TestRecordBysubjectId(Long courseId) {
		return this.testRecordRepository.findAllByCourseId(courseId);
	}
	public Page<TestRecord>searchTestRecord(Long id, Long studentId, Long courseId,Integer pageNo,Integer pageSize,String fieldName,Direction direction) {
		Page<TestRecord> tutorCourse=testRecordRepository.searchTestRecordByAnyField(PageRequest.of(pageNo,pageSize,Sort.by(direction, fieldName)),id,studentId,courseId);
		return tutorCourse;
	}
}

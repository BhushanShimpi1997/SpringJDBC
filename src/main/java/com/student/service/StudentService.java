package com.student.service;

import com.student.exception.ResourceNotFoundException;
import com.student.models.Student;
import com.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService {

    private StudentRepository repository;
    //create student business logic
    public Student createStudent(Student student){
        int count=repository.createStudent(student);
        log.info("rows updated:{}",count);
        return student;
    }

    public List<Student> getStudents() {
        log.info("calling repo to get all students");
        List<Student> stList = repository.getAll();
        return stList;
    }

    public Student getStudents(int rollNo){
        Student student=repository.getStudentByRollNo(rollNo)
                .orElseThrow(()->{throw  new ResourceNotFoundException("Student Not Found with RollNo: "+rollNo);});
        log.info("returning student from DB:{}",student);
        return student;
    }

    public Student updateStudent(Student student, int rollNo){
        log.info("calling getStudent() with rollNo:{}",rollNo);
        Student existingSt = getStudents(rollNo);
        log.info("existing student details:{}",existingSt);
        existingSt.setStudentName(student.getStudentName());
        existingSt.setCity(student.getCity());
        existingSt.setMobileNumber(student.getMobileNumber());
        existingSt.setUpdatedAt(LocalDateTime.now());
        log.info("updated details:{}",existingSt);
        repository.updateStudentByRollNo(existingSt, rollNo);
        return existingSt;
    }

    public void deleteStudent(int rollNo){
        int row = repository.delete(rollNo);
        log.info("rows affected:{}",row);
        if(!(row >0)){
            throw  new ResourceNotFoundException("Student Not Found with RollNo: "+rollNo);
        }
    }
}

package com.student.controller;

import com.student.models.Student;
import com.student.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(path = "/student")
@Validated
public class StudentController {

    StudentService service;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        log.info("into welcome method..");
        return "welcome";
    }

    @PostMapping("/st/{id}")
    public ResponseEntity<String> validatedPathVariable(@PathVariable("id") @Min(5) int id){
        log.info("inside validatedPathVariable Id:{}",id);
        return ResponseEntity.ok("valid");
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Student> createStudentHandler(@Valid @RequestBody Student student) {
        log.info("request body: {}",student);
        Student st=service.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Student>> getAllStudentsHandler(){
        log.info("Calling Service for list of students");
        List<Student> allStudents = service.getStudents();
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping(path = "/{rollNo}")
    public ResponseEntity<Student> getStudentHandler(@PathVariable int rollNo){
        log.info("student rollNo in Request:{}",rollNo);
        Student student = service.getStudents(rollNo);
        return ResponseEntity.ok(student);
    }

    @PutMapping(path = "/{rollNo}")
    public ResponseEntity<Student> updateStudentHandler(@Valid @RequestBody Student student, @PathVariable int rollNo){
        log.info("Request Body:{}",student);
        Student updatedSt = service.updateStudent(student, rollNo);
        return ResponseEntity.accepted().body(updatedSt);
    }

    @DeleteMapping(path = "/{rollNo}")
    public ResponseEntity<String> deleteStudentHandler(@PathVariable int rollNo){
        log.info("delete request for rollNO:{}",rollNo);
        service.deleteStudent(rollNo);
        return ResponseEntity.ok("Student Deleted SuccessFully !!");
    }

}
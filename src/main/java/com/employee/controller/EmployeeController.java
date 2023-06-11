package com.employee.controller;

import com.employee.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1-api")
@Slf4j
public class EmployeeController {

    @PostMapping(path="/create")
    public ResponseEntity<Employee> createStudent(@RequestBody Employee employee){
        log.info("employee infor in request:{}", employee);
        return ResponseEntity.ok(employee);
    }
}

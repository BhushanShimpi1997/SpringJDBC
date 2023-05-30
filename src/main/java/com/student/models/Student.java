package com.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    private int rollNo;
    @NotBlank(message = "student name can not be empty!!")
    private String studentName;
    @NotEmpty(message = "city cann't be empty!!")
    private String city;
    @NotBlank(message = "message can't be blank")
    private String mobileNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

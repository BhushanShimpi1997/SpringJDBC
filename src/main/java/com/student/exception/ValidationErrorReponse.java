package com.student.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ValidationErrorReponse {

    List<Violation> violations=new ArrayList<>();
   // Map<String,String> violations=new HashMap<>();
}

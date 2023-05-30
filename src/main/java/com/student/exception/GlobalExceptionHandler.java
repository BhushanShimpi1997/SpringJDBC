package com.student.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorReponse onMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ValidationErrorReponse error=new ValidationErrorReponse();
      /*  for (FieldError fieldError: e.getBindingResult().getFieldErrors()){
            error.getViolations()
                    .add(new Violation(fieldError.getField(),fieldError.getDefaultMessage())); //for list
         //   error.getViolations().put(fieldError.getField(),fieldError.getDefaultMessage()); //for map
        }*/
        e.getBindingResult().getFieldErrors()
                .forEach(e1-> error.getViolations()
                        .add(new Violation(e1.getField(),e1.getDefaultMessage())));
        return error;
    }

    @ExceptionHandler(value=ConstraintViolationException.class)
    ResponseEntity<ValidationErrorReponse> onConstraintViolationException(ConstraintViolationException e){
        ValidationErrorReponse error=new ValidationErrorReponse();
        for(ConstraintViolation violation : e.getConstraintViolations()){
            error.getViolations()
                    .add(new Violation(violation.getPropertyPath().toString(),violation.getMessage()));
        }
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> onResourceNotFoundException(ResourceNotFoundException e){
        log.info("exception message:{}",e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

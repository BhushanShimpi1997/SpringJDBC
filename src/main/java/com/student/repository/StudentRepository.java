package com.student.repository;

import com.student.models.Student;
import com.student.rowmapper.StudentRowMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
@AllArgsConstructor
public class StudentRepository {

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedTemplate;
    //create Student
    public int createStudent(Student student){
        student.setCreatedAt(LocalDateTime.now());
        log.info("student obj:{}",student);
        String query="INSERT INTO STUDENT(studentName,city,mobileNumber,createdAt)"
                      +"values(?,?,?,?)";
        return template.update(query,
                               student.getStudentName(),student.getCity(),
                               student.getMobileNumber(),student.getCreatedAt());
    }

    public List<Student> getAll() {
        String sql="select * from student";
        log.info("executing Query:{}",sql);
        final List<Student> result = namedTemplate.query(sql, new StudentRowMapper());
        log.info("response from DB:{}",result);
        return result;
    }

    public Optional<Student> getStudentByRollNo(int rollNo){
        String sql="select * from student where rollNo=:roll";
        SqlParameterSource parameterSource=new MapSqlParameterSource("roll",rollNo);
        final List<Student> query = namedTemplate.query(sql, parameterSource, new StudentRowMapper());
        Optional<Student> student = query.stream().findFirst();
        return student;
    }

    public Student updateStudentByRollNo(Student student,int rollNo){
        String query="update student set studentName=:name,mobileNumber=:number," +
                     "city=:city,updatedAt=:update where rollNo=:roll";
        Map<String,Object> paramList=new LinkedHashMap<>();
        paramList.put("name",student.getStudentName());
        paramList.put("number",student.getMobileNumber());
        paramList.put("city",student.getCity());
        paramList.put("update",student.getUpdatedAt());
        paramList.put("roll",rollNo);
        SqlParameterSource parameterSource=new MapSqlParameterSource(paramList);
        final int result = namedTemplate.update(query, parameterSource);
        log.info("rows Affected:{}",result);
        return student;
    }

    public int delete(int rollNo){
        String sql="DELETE from STUDENT where rollNo=:roll";
        SqlParameterSource parameterSource=new MapSqlParameterSource("roll",rollNo);
        final int row = namedTemplate.update(sql, parameterSource);
        return row;
    }
}

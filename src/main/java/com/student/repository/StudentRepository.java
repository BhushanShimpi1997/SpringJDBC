package com.student.repository;

import com.student.models.Student;
import com.student.rowmapper.StudentRowMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class StudentRepository {

    private JdbcTemplate template;
    @Autowired
    public StudentRepository(JdbcTemplate template){
        this.template=template;
        String sql="CREATE table IF NOT EXISTS student(rollNo int AUTO_INCREMENT primary key," +
                "studentName varchar(50)Not Null," +
                "city varchar(30)," +
                "mobileNumber varchar(12) not null," +
                "createdAt TIMESTAMP DEFAULT NULL ," +
                "updatedAt TIMESTAMP DEFAULT NULL);";
        template.execute(sql);
    }

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
        List<Student> result = template.query(sql, new StudentRowMapper());
        log.info("response from DB:{}",result);
        return result;
    }

    public Optional<Student> getStudentByRollNo(int rollNo){
        String sql="select * from student where rollNo=?";
        List<Student> query = template.query(sql, new StudentRowMapper(), rollNo);
        Optional<Student> student = query.stream().findFirst();
        return student;
    }

    public Student updateStudentByRollNo(Student student,int rollNo){
        String query="update student set studentName=?,mobileNumber=?,city=?,updatedAt=? where rollNo=?";
        int result = template.update(query, student.getStudentName(), student.getMobileNumber(),
                                            student.getCity(), student.getUpdatedAt(), rollNo);
        log.info("rows Affected:{}",result);
        return student;
    }

    public int delete(int rollNo){
        String sql="DELETE from STUDENT where rollNo=?";
        int row = template.update(sql, rollNo);
        return row;
    }
}

package com.student.rowmapper;

import com.student.models.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student st=new Student();
        st.setRollNo(rs.getInt("rollNo"));
        st.setStudentName(rs.getString("studentName"));
        st.setMobileNumber(rs.getString("mobileNumber"));
        st.setCity(rs.getString("city"));
        st.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        if (rs.getString("updatedAt") !=null){
            st.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());
        }
        return st;
    }
}

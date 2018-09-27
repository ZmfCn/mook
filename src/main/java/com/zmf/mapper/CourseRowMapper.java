package com.zmf.mapper;

import com.zmf.pojo.Course;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseRowMapper implements RowMapper<Course> {
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getString(1));
        course.setCourseTypeId(resultSet.getString(2));
        course.setTeacherName(resultSet.getString(3));
        course.setCharpterNumber(resultSet.getByte(4));
        course.setImagePath(resultSet.getString(5));
        course.setCoursePath(resultSet.getString(6));
        course.setCourseName(resultSet.getString(7));
        course.setCourseCount(resultSet.getByte(8));
        return course;
    }
}

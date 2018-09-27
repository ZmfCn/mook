package com.zmf.mapper;

import com.zmf.pojo.CourseType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseTypeRowMapper implements RowMapper<CourseType> {
    public CourseType mapRow(ResultSet resultSet, int i) throws SQLException {
        CourseType courseType = new CourseType();
        courseType.setTypeId(resultSet.getString(1));
        courseType.setTypeName(resultSet.getString(2));
        courseType.setTopTypeId(resultSet.getString(3));
        return courseType;
    }
}

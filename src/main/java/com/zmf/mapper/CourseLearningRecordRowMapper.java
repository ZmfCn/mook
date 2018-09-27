package com.zmf.mapper;

import com.zmf.pojo.CourseLearningRecord;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseLearningRecordRowMapper implements RowMapper<CourseLearningRecord> {

    public CourseLearningRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        CourseLearningRecord courseLearningRecord = new CourseLearningRecord();
        courseLearningRecord.setRecordId(resultSet.getString(1));
        courseLearningRecord.setCourseId(resultSet.getString(2));
        courseLearningRecord.setUserId(resultSet.getString(3));
        courseLearningRecord.setStartTime(resultSet.getTimestamp(4));
        return courseLearningRecord;
    }
}

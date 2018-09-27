package com.zmf.mapper;

import com.zmf.pojo.ChapterLearningRecord;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChapterLearningRecordRowMapper implements RowMapper<ChapterLearningRecord> {
    public ChapterLearningRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        ChapterLearningRecord chapterLearningRecord=new ChapterLearningRecord();
        chapterLearningRecord.setRecordId(resultSet.getString(1));
        chapterLearningRecord.setUserId(resultSet.getString(2));
        chapterLearningRecord.setChapterId(resultSet.getString(3));
        chapterLearningRecord.setLastLearn(resultSet.getTimestamp(4));
        return chapterLearningRecord;
    }
}

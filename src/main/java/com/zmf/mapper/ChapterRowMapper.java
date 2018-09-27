package com.zmf.mapper;

import com.zmf.pojo.Chapter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChapterRowMapper implements RowMapper<Chapter> {

    public Chapter mapRow(ResultSet resultSet, int i) throws SQLException {
        Chapter chapter = new Chapter();
        chapter.setChapterId(resultSet.getString(1));
        chapter.setOwnerCourseId(resultSet.getString(2));
        chapter.setChapterTitle(resultSet.getString(3));
        chapter.setIsPdf(resultSet.getBoolean(4));
        chapter.setFilepath(resultSet.getString(5));
        chapter.setDuration(resultSet.getString(6));
        chapter.setChapterOrder(resultSet.getInt(7));
        return chapter;
    }
}

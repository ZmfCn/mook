package com.zmf.mapper;

import com.zmf.pojo.ChapterComment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChapterCommentRowMapper implements RowMapper<ChapterComment> {
    public ChapterComment mapRow(ResultSet resultSet, int i) throws SQLException {
        ChapterComment chapterComment = new ChapterComment();
        chapterComment.setCommentId(resultSet.getString(1));
        chapterComment.setChapterId(resultSet.getString(2));
        chapterComment.setCommentUserId(resultSet.getString(3));
        chapterComment.setIschecked(resultSet.getBoolean(4));
        chapterComment.setIsBanned(resultSet.getBoolean(5));
        chapterComment.setContent(resultSet.getString(6));
        chapterComment.setCommentTime(resultSet.getTimestamp(7));
        return chapterComment;
    }
}

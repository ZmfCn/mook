package com.zmf.dao;

import com.zmf.mapper.ChapterLearningRecordRowMapper;
import com.zmf.pojo.ChapterLearningRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChapterLearningRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ChapterLearningRecordRowMapper chapterLearningRecordRowMapper;

    //C
    public void addChapterLearningRecord(ChapterLearningRecord chapterLearningRecord) {
        String sql = "INSERT INTO `chapter_learning_record`\n" +
                "(`record_id`,\n" +
                "`user_id`,\n" +
                "`chapter_id`,\n" +
                "`last_learn`)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?);\n";
        jdbcTemplate.update(sql,
                chapterLearningRecord.getRecordId(),
                chapterLearningRecord.getUserId(),
                chapterLearningRecord.getChapterId(),
                chapterLearningRecord.getLastLearn()
        );
    }

    //R
    public ChapterLearningRecord getChapterLearningRecordById(String chapterLearningRecordId) {
        String sql = "SELECT * FROM chapter_learning_record WHERE record_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, chapterLearningRecordRowMapper, chapterLearningRecordId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChapterLearningRecord> getChapterLearningRecordsByUserId(String userId) {
        String sql = "SELECT * FROM chapter_learning_record WHERE user_id=?";
        try {
            return jdbcTemplate.query(sql, chapterLearningRecordRowMapper, userId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<ChapterLearningRecord> getChapterLearningRecordsByChapterId(String chapterId) {
        String sql = "SELECT * FROM chapter_learning_record WHERE chapter_id=?";
        try {
            return jdbcTemplate.query(sql, chapterLearningRecordRowMapper, chapterId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    //U
    public void updateChapterLearningRecordById(String chapterLearningRecordId, ChapterLearningRecord chapterLearningRecord) {
        String sql = "UPDATE `chapter_learning_record`\n" +
                "SET\n" +
                "`record_id` = ?,\n" +
                "`user_id` = ?,\n" +
                "`chapter_id` = ?,\n" +
                "`last_learn` = ?\n" +
                "WHERE `record_id` = ?;\n";
        jdbcTemplate.update(sql,
                chapterLearningRecord.getRecordId(),
                chapterLearningRecord.getUserId(),
                chapterLearningRecord.getChapterId(),
                chapterLearningRecord.getLastLearn(),
                chapterLearningRecordId);
    }

    //D
    public void deleteChapterLearningRecord(String chapterLearningRecordId) {
        String sql = "DELETE FROM chapter_learning_record WHERE record_id=?";
        jdbcTemplate.update(sql, chapterLearningRecordId);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
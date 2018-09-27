package com.zmf.dao;

import com.zmf.mapper.CourseLearningRecordRowMapper;
import com.zmf.pojo.CourseLearningRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseLearningRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseLearningRecordRowMapper courseLearningRecordRowMapper;
    private Logger logger = Logger.getLogger(CourseLearningRecordDao.class);

    /**
     * add course learning record
     *
     * @param courseLearningRecord the record
     */
    public void addCourseLearningRecord(CourseLearningRecord courseLearningRecord) {
        logger.info("course learning record: add a record... id: " + courseLearningRecord.getRecordId());
        String sql = "INSERT INTO course_learning_record(record_id,course_id,user_id,start_time) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                courseLearningRecord.getRecordId(),
                courseLearningRecord.getCourseId(),
                courseLearningRecord.getUserId(),
                courseLearningRecord.getStartTime()
        );
    }

    /**
     * get course learning record by id
     *
     * @param courseLearningRecordId the id
     * @return the record
     */
    public CourseLearningRecord getCourseLearningRecordById(String courseLearningRecordId) {
        logger.info("course learning record: get course learning record by id... id: " + courseLearningRecordId);
        String sql = "SELECT * FROM course_learning_record WHERE record_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, courseLearningRecordRowMapper, courseLearningRecordId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get course learning record by course id
     *
     * @param courseId the course id
     * @return the records
     */
    public List<CourseLearningRecord> getCourseLearningRecordsByCourseId(String courseId) {
        logger.info("course learning record: get course learning record by course id ...course id: " + courseId);
        String sql = "SELECT * FROM course_learning_record WHERE course_id=?";
        try {
            return jdbcTemplate.query(sql, courseLearningRecordRowMapper, courseId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get course learning records by user id
     *
     * @param userId the user id
     * @return the records
     */
    public List<CourseLearningRecord> getCourseLearningRecordsByUserId(String userId) {
        logger.info("course learning record: get course learning records by user id... user id: " + userId);
        String sql = "SELECT * FROM course_learning_record WHERE user_id=?";
        try {
            return jdbcTemplate.query(sql, courseLearningRecordRowMapper, userId);
        } catch (Exception e) {
            return null;
        }
    }

    //U
    public void updateCourseLearningRecordById(String courseLearningRecordId, CourseLearningRecord courseLearningRecord) {
        String sql = "UPDATE `course_learning_record`\n" +
                "SET\n" +
                "`record_id` = ?,\n" +
                "`course_id` = ?,\n" +
                "`user_id` = ?,\n" +
                "`start_time` = ?\n" +
                "WHERE `record_id` = ?;\n";
        jdbcTemplate.update(sql,
                courseLearningRecord.getRecordId(),
                courseLearningRecord.getCourseId(),
                courseLearningRecord.getUserId(),
                courseLearningRecord.getStartTime(),
                courseLearningRecordId);
    }

    /**
     * delete records by course id
     *
     * @param courseId the course id
     */
    public void deleteRecordsByCourseId(String courseId) {
        logger.info("course learning record: delete records by course id ... course id: " + courseId);
        String sql = "DELETE FROM course_learning_record WHERE course_id = ?";
        jdbcTemplate.update(sql, courseId);
    }

    /**
     * delete course learning record by id
     *
     * @param courseLearningRecordId the record id
     */
    public void deleteCourseLearningRecord(String courseLearningRecordId) {
        logger.info("course learning record: delete course learning record by id... id: " + courseLearningRecordId);
        String sql = "DELETE FROM course_learning_record WHERE record_id=?";
        jdbcTemplate.update(sql, courseLearningRecordId);
    }

    /**
     * get course learning record by the user id and course id
     *
     * @param courseId the course id
     * @param userId   the user id
     * @return the course or null
     */
    public CourseLearningRecord getRecordByUserIdAndCourseId(String courseId, String userId) {
        logger.info("course learning record: get course learning record by the user id and course id... course id: " + courseId + " user id: " + userId);
        String sql = "SELECT * FROM course_learning_record WHERE course_id = ? AND user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, courseLearningRecordRowMapper, courseId, userId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get the record number by the user id
     *
     * @param userId the user id
     * @return the number
     */
    public int getRecordNumberByUserId(String userId) {
        logger.info("course learning record: get the record number by the user id... user id: " + userId);
        String sql = "SELECT count(*) FROM course_learning_record WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, userId);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

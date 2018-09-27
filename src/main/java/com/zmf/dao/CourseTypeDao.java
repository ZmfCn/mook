package com.zmf.dao;

import com.zmf.mapper.CourseTypeRowMapper;
import com.zmf.pojo.CourseType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseTypeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseTypeRowMapper courseTypeRowMapper;
    private Logger logger = Logger.getLogger(CourseTypeDao.class);


    /**
     * get all the course types
     *
     * @return the records
     */
    public List<CourseType> getAllCourseTypes() {
        logger.info("course type: get all the course types...");
        String sql = "SELECT * FROM course_type";
        return jdbcTemplate.query(sql, courseTypeRowMapper);
    }

    /**
     * add a course type
     *
     * @param courseType course type
     */
    public void addCourseType(CourseType courseType) {
        logger.info("course type: add a course type... type name: " + courseType.getTypeName());
        String sql = "INSERT INTO course_type\n" +
                "(`type_id`,\n" +
                "`type_name`,\n" +
                "`top_type_id`)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?);\n";
        jdbcTemplate.update(sql,
                courseType.getTypeId(),
                courseType.getTypeName(),
                courseType.getTopTypeId()
        );
    }

    /**
     * get course type by id
     *
     * @param courseTypeId the id
     * @return the course type
     */
    public CourseType getCourseTypeById(String courseTypeId) {
        logger.info("course type: get course type by id... id: " + courseTypeId);
        String sql = "SELECT * FROM course_type WHERE type_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, courseTypeRowMapper, courseTypeId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get course type by name
     *
     * @param typeName the type name
     * @return the course type
     */
    public CourseType getCourseTypeByTypeName(String typeName) {
        logger.info("course type: get course type by name... name: " + typeName);
        String sql = "SELECT * FROM course_type WHERE type_name=?";
        try {
            return jdbcTemplate.queryForObject(sql, courseTypeRowMapper, typeName);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * get course type by top type id
     *
     * @param topTypeId the top type id
     * @return the records;
     */
    public List<CourseType> getCourseTypesByTopTypeId(String topTypeId) {
        logger.info("course type: get course type by top type id... top type id: " + topTypeId);
        String sql = "SELECT * FROM course_type WHERE top_type_id=?";
        try {
            return jdbcTemplate.query(sql, courseTypeRowMapper, topTypeId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    /**
     * update course type by id
     *
     * @param courseType the course type
     */
    public void updateCourseTypeById(CourseType courseType) {
        logger.info("course type: update course type by id... id: " + courseType.getTypeId());
        String sql = "UPDATE `course_type`\n" +
                "SET\n" +
                "`type_id` = ?,\n" +
                "`type_name` = ?,\n" +
                "`top_type_id` = ?\n" +
                "WHERE `type_id` = ?;\n";
        jdbcTemplate.update(sql,
                courseType.getTypeId(),
                courseType.getTypeName(),
                courseType.getTopTypeId(),
                courseType.getTypeId());
    }

    /**
     * delete the course type by id
     *
     * @param courseTypeId course type id
     */
    public void deleteCourseType(String courseTypeId) {
        logger.info("course type: delete the course type by id... id: " + courseTypeId);
        String sql = "DELETE FROM course_type WHERE type_id=?";
        jdbcTemplate.update(sql, courseTypeId);
    }

    /**
     * get all course types by top type id
     *
     * @param topTypeId the top type id
     * @return the records
     */
    public List<CourseType> getAllCourseTypesByParentTypeId(String topTypeId) {
        logger.info("course type: get all course types by top type id... id: " + topTypeId);
        String sql = "SELECT * FROM course_type WHERE top_type_id = ?";
        try {
            return jdbcTemplate.query(sql, courseTypeRowMapper, topTypeId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

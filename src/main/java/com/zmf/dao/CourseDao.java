package com.zmf.dao;

import com.zmf.mapper.CourseRowMapper;
import com.zmf.pojo.Course;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseRowMapper courseRowMapper;
    private Logger logger = Logger.getLogger(CourseDao.class);

    /**
     * add a course
     *
     * @param course the course
     */
    public void addCourse(Course course) {
        logger.info("course: add a course... course name: " + course.getCourseName());
        String sql = "INSERT INTO `course`\n" +
                "(`course_id`,\n" +
                "`course_type_id`,\n" +
                "`teacher_name`,\n" +
                "`charpter_number`,\n" +
                "`image_path`,\n" +
                "`course_path`,\n" +
                "`course_name`,\n" +
                "`course_count`)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);\n";
        jdbcTemplate.update(sql,
                course.getCourseId(),
                course.getCourseTypeId(),
                course.getTeacherName(),
                course.getCharpterNumber(),
                course.getImagePath(),
                course.getCoursePath(),
                course.getCourseName(),
                course.getCourseCount());
    }

    /**
     * get the course by the comment id
     *
     * @param commentId the chapter comment id
     * @return the course
     */
    public Course getCourseByCommentId(String commentId) {
        logger.info("course: get the course by the comment id... id: " + commentId);
        String sql = "SELECT DISTINCT course.* FROM chapter,course,chapter_comment WHERE chapter_comment.comment_id = ? AND chapter.chapter_id = chapter_comment.chapter_id AND chapter.owner_course_id = course.course_id";
        try {
            return jdbcTemplate.queryForObject(sql, courseRowMapper, commentId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get the course by the course name
     *
     * @param courseName the course name
     * @return the course
     */
    public Course getCourseByName(String courseName) {
        logger.info("course: get the course by the course name... name: " + courseName);
        String sql = "SELECT * FROM course WHERE course_name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, courseRowMapper, courseName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get the course by id
     *
     * @param courseId the course id
     * @return the course
     */
    public Course getCourseById(String courseId) {
        logger.info("course: get the course by id... id: " + courseId);
        String sql = "SELECT * FROM course WHERE course_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, courseRowMapper, courseId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get the course by the course type id
     *
     * @param courseTypeId the course type id
     * @return the list that contains results
     */
    public List<Course> getCourseByCourseTypeId(String courseTypeId) {
        logger.info("course: get the course by the course type id... type id: " + courseTypeId);
        String sql = "SELECT * FROM course WHERE course_type_id=?";
        try {
            return jdbcTemplate.query(sql, courseRowMapper, courseTypeId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /***
     * get several records from somewhere certain
     * @param courseTypeId the course type id
     * @param startPosition the start position
     * @param number the number of records to be got
     * @return the records
     */
    public List<Course> getSeveralCoursesByCourseTypeId(String courseTypeId, int startPosition, int number) {
        logger.info("course: get several records from somewhere certain... course type id: " + courseTypeId);
        String sql = "SELECT * FROM course WHERE course_type_id=? ORDER BY course_id LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, courseRowMapper, courseTypeId, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get several courses that the user has learnt by the user id
     *
     * @param userId        the user id
     * @param startPosition the start position
     * @param number        the number of records
     * @return the list that contains results
     */
    public List<Course> getSeveralCoursesByUserId(String userId, int startPosition, int number) {
        logger.info("course: get several courses that the user has learnt by the user id... user id: " + userId);
        String sql = "SELECT c.* FROM course c INNER JOIN course_learning_record r ON c.course_id = r.course_id WHERE user_id = ? ORDER BY start_time LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, courseRowMapper, userId, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get all courses
     *
     * @return the list that contains results
     */
    public List<Course> getAllCourses() {
        logger.info("course: get all courses...");
        String sql = "SELECT * FROM course";
        try {
            return jdbcTemplate.query(sql, courseRowMapper);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get the number of all the courses
     *
     * @return the number
     */
    public int getCourseNumber() {
        logger.info("course: get the number of all the courses...");
        String sql = "SELECT COUNT(*) FROM course";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * get several courses
     *
     * @param startPosition the start position
     * @param number        the number
     * @return the list that contains results
     */
    public List<Course> getSeveralCoursesInAll(int startPosition, int number) {
        logger.info("course: get several courses...");
        String sql = "SELECT * FROM course ORDER BY course_id LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, courseRowMapper, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get the courses by the teacher name
     *
     * @param teacherName the teacher name
     * @return the list that contains results
     */
    public Course getCourseByTeacherName(String teacherName) {
        logger.info("course: get the courses by the teacher name...");
        String sql = "SELECT * FROM course WHERE teacher_name=?";
        try {
            return jdbcTemplate.queryForObject(sql, courseRowMapper, teacherName);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * update course by id
     *
     * @param course the course
     */
    public void updateCourseById(Course course) {
        logger.info("course: update course by id... id: " + course.getCourseId());
        String sql = "UPDATE `course`\n" +
                "SET\n" +
                "`course_id` = ?,\n" +
                "`course_type_id` = ?,\n" +
                "`teacher_name` = ?,\n" +
                "`charpter_number` = ?,\n" +
                "`image_path` = ?,\n" +
                "`course_path` =?,\n" +
                "`course_name` = ?,\n" +
                "`course_count` = ?\n" +
                "WHERE `course_id` = ?;\n";
        jdbcTemplate.update(sql,
                course.getCourseId(),
                course.getCourseTypeId(),
                course.getTeacherName(),
                course.getCharpterNumber(),
                course.getImagePath(),
                course.getCoursePath(),
                course.getCourseName(),
                course.getCourseCount(),
                course.getCourseId());
    }

    /**
     * delete the course by id
     *
     * @param courseId the course id
     */
    public void deleteCourse(String courseId) {
        logger.info("course: delete the course by id... id: " + courseId);
        String sql = "DELETE FROM course WHERE course_id=?";
        jdbcTemplate.update(sql, courseId);
    }

    /**
     * get all courses by course type id
     *
     * @param courseTypeId the course type id
     * @return the list that contains results
     */
    public List<Course> getAllCoursesByCourseTypeId(String courseTypeId) {
        logger.info("course: get all courses by course type id...");
        String sql = "SELECT * FROM course WHERE course_type_id = ?";
        try {
            return jdbcTemplate.query(sql, courseRowMapper, courseTypeId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get the number of courses in a course type
     *
     * @param courseTypeId the course type id
     * @return the number
     */
    public int getSubCourseNumberByCourseTypeId(String courseTypeId) {
        logger.info("course: get the number of courses in a course type... course type id: " + courseTypeId);
        String sql = "SELECT count(*) FROM course WHERE course_type_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, courseTypeId);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * get courses like something
     *
     * @param string        the string to match
     * @param startPosition the start position
     * @param number        the number
     * @return the courses
     */
    public List<Course> getSeveralCourseLike(String string, int startPosition, int number) {
        logger.info("course: get courses like something: string: " + string);
        String sql = "SELECT * FROM course WHERE course_name LIKE '%" + string + "%' ORDER BY course_id LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, courseRowMapper, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    /**
     * the number of the course like
     *
     * @param string the string to match
     * @return the number
     */
    public int getTheNumberOfCourseLike(String string) {
        logger.info("course: the number of the course like something: string: " + string);
        String sql = "SELECT COUNT(*) FROM course WHERE course_name LIKE '%" + string + "%'";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * add count of the courseId
     *
     * @param courseId course id
     */
    public void addCount(String courseId) {
        String sql = "UPDATE course SET course_count = course_count + 1 WHERE course_id = ?";
        jdbcTemplate.update(sql, courseId);
    }

//    /**
//     * get several records from somewhere certain
//     *
//     * @param userId        the user id
//     * @param startPosition the start position
//     * @param number        the number of records to be got
//     * @return the records
//     */
//    public List<Course> getSeveralCourseLearntByUserId(String userId, int startPosition, int number) {
//        logger.info("course: add a course...");
//        String sql = "SELECT course.* FROM course JOIN course_learning_record ON course.course_id = course_learning_record.course_id WHERE user_id = ? ORDER BY course_learning_record.start_time LIMIT ?,?";
//        try {
//            return jdbcTemplate.query(sql, courseRowMapper, userId, startPosition, number);
//        } catch (Exception e) {
//            return new ArrayList<>();
//        }
//    }
//
//    public int getCourseNumberLearntByUserId(String userId) {
//        logger.info("course: add a course...");
//        String sql = "SELECT count(*) FROM course JOIN course_learning_record ON course.course_id = course_learning_record.course_id WHERE user_id = ?";
//        try {
//            return jdbcTemplate.queryForObject(sql, Integer.class, userId);
//        } catch (Exception e) {
//            return 0;
//        }
//    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

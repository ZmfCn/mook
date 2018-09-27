package com.zmf.dao;

import com.zmf.mapper.ChapterRowMapper;
import com.zmf.pojo.Chapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChapterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ChapterRowMapper chapterRowMapper;
    private Logger logger = Logger.getLogger(ChapterDao.class);

    /**
     * add the chapter to the database
     *
     * @param chapter the chapter instance that contains all of the information
     */
    public void addChapter(Chapter chapter) {
        logger.info("chapter: add the chapter to the database... chapter name: " + chapter.getChapterTitle());
        String sql = "INSERT INTO chapter(chapter_id, owner_course_id, chapter_title ,is_pdf, filepath,duration,chapter_order) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                chapter.getChapterId(),
                chapter.getOwnerCourseId(),
                chapter.getChapterTitle(),
                chapter.getIsPdf(),
                chapter.getFilepath(),
                chapter.getDuration(),
                chapter.getChapterOrder()
        );
    }

    /**
     * update the chapter order by the chapter id
     *
     * @param chapter the chapter that contains all of the information
     */
    public void updateChapterOrder(Chapter chapter) {
        logger.info("chapter: update the chapter order by the chapter id... id: " + chapter.getChapterId());
        String sql = "UPDATE chapter SET chapter_order = ? WHERE chapter_id = ?";
        jdbcTemplate.update(sql, chapter.getChapterOrder(), chapter.getChapterId());
    }

    /**
     * update the chapter duration
     *
     * @param chapterId chapter id
     * @param duration  the duration
     */
    public void updateChapterDuration(String chapterId, String duration) {
        logger.info("chapter: update the chapter duration... chapter id: " + chapterId + " duration: " + duration);
        String sql = "UPDATE chapter SET duration = ? WHERE chapter_id = ?";
        jdbcTemplate.update(sql, duration, chapterId);
    }

    /**
     * get chapter by id
     *
     * @param chapterId the chapter id
     * @return the chapter if exists
     */
    public Chapter getChapterById(String chapterId) {
        logger.info("chapter: get chapter by id... id: " + chapterId);
        String sql = "SELECT * FROM chapter WHERE chapter_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, chapterRowMapper, chapterId);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * get a chapter of a course by the chapter order and chapter owner course id
     *
     * @param chapterOrder the order of the chapter
     * @param courseId     the course id of the owner of the chapter
     * @return the chapter if it exists or null
     */
    public Chapter getChapterByOrderAndCourseId(int chapterOrder, String courseId) {
        logger.info("chapter: get a chapter of a course by the chapter order and chapter owner course id... chapter order: " + chapterOrder + " course id: " + courseId);
        String sql = "SELECT * FROM chapter WHERE chapter_order = ? AND owner_course_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, chapterRowMapper, chapterOrder, courseId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get all the chapters of the certain course
     *
     * @param ownerCourseId the owner course id
     * @return the list that contains all chapters arranged in increasing order on chapter order
     */
    public List<Chapter> getChaptersByOwnerCourseId(String ownerCourseId) {
        logger.info("chapter: get all the chapters of the certain course... course id: " + ownerCourseId);
        String sql = "SELECT * FROM chapter WHERE owner_course_id=? ORDER BY chapter_order";
        try {
            return jdbcTemplate.query(sql, chapterRowMapper, ownerCourseId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get the chapters that has an order that is bigger than certain chapter order
     *
     * @param ownerCourseId the owner course id
     * @param chapterOrder  the chapter order
     * @return the list of chapters
     */
    public List<Chapter> getChaptersAfterCertainOrder(String ownerCourseId, int chapterOrder) {
        logger.info("chapter: get the chapters that has an order that is bigger than certain chapter order... course id: " + ownerCourseId + " chapter order: " + chapterOrder);
        String sql = "SELECT * FROM chapter WHERE owner_course_id = ? AND chapter_order >= ?";
        try {
            return jdbcTemplate.query(sql, chapterRowMapper, ownerCourseId, chapterOrder);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get several chapters that has an order that is bigger than certain chapter order
     *
     * @param ownerCourseId the owner course id
     * @param chapterOrder  the chapter order
     * @param size          the number of the records to be got
     * @return the list of chapters
     */
    public List<Chapter> getSeveralChaptersAfterCertainOrder(String ownerCourseId, int chapterOrder, int size) {
        logger.info("chapter: get several chapters that has an order that is bigger than certain chapter order...");
        String sql = "SELECT * FROM chapter WHERE owner_course_id = ? AND chapter_order >= ? ORDER BY chapter_order LIMIT ?";
        try {
            return jdbcTemplate.query(sql, chapterRowMapper, ownerCourseId, chapterOrder, size);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get how many chapters do the course have
     *
     * @param ownerCourseId the course id
     * @return the chapter number
     */
    public int getChapterNumberByOwnerCourseId(String ownerCourseId) {
        logger.info("chapter: get how many chapters do the course have...");
        String sql = "SELECT COUNT(*) FROM chapter WHERE owner_course_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, ownerCourseId);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * get chapter by the chapter title
     *
     * @param title the chapter title
     * @return the chapter if exists
     */
    public Chapter getChapterByTitle(String title) {
        logger.info("chapter: get chapter by the chapter title...");
        String sql = "SELECT * FROM chapter WHERE chapter_title=?";
        try {
            return jdbcTemplate.queryForObject(sql, chapterRowMapper, title);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get the chapter by the comment id
     *
     * @param commentId the comment id
     * @return the chapter
     */
    public Chapter getChapterByCommentId(String commentId) {
        logger.info("chapter: get the chapter by the comment id... id: " + commentId);
        String sql = "SELECT DISTINCT chapter.* FROM chapter NATURAL JOIN chapter_comment WHERE comment_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, chapterRowMapper, commentId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * update the chapter by the chapter id
     *
     * @param chapter the chapter that contains all the information
     */
    public void updateChapterById(Chapter chapter) {
        logger.info("chapter: update the chapter by the chapter id... id: " + chapter.getChapterId());
        String sql = "UPDATE chapter SET chapter_id = ? , owner_course_id = ?, chapter_title = ? ,is_pdf = ?, filepath = ?,duration = ?,chapter_order = ? WHERE chapter_id = ?";
        jdbcTemplate.update(sql,
                chapter.getChapterId(),
                chapter.getOwnerCourseId(),
                chapter.getChapterTitle(),
                chapter.getIsPdf(),
                chapter.getFilepath(),
                chapter.getDuration(),
                chapter.getChapterOrder(),
                chapter.getChapterId()
        );
    }

    /**
     * delete the chapter by id
     *
     * @param chapterId the chapter id
     */
    public void deleteChapter(String chapterId) {
        logger.info("chapter: delete the chapter by id... id: " + chapterId);
        String sql = "DELETE FROM chapter WHERE chapter_id=?";
        jdbcTemplate.update(sql, chapterId);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

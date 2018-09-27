package com.zmf.dao;

import com.zmf.mapper.ChapterCommentRowMapper;
import com.zmf.pojo.ChapterComment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChapterCommentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ChapterCommentRowMapper chapterCommentRowMapper;
    private Logger logger = Logger.getLogger(ChapterCommentDao.class);


    public void addCourseComment(ChapterComment chapterComment) {
        logger.info("chapter comment: add chapter comment... content: " + chapterComment.getContent());
        String sql = "INSERT INTO chapter_comment (comment_id,chapter_id,comment_user_id,comment_time,isChecked, is_banned, content) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                chapterComment.getCommentId(),
                chapterComment.getChapterId(),
                chapterComment.getCommentUserId(),
                chapterComment.getCommentTime(),
                chapterComment.getIschecked(),
                chapterComment.getIsBanned(),
                chapterComment.getContent()
        );
    }

    public void updateIsToBeBanned(String chapterCommentId, boolean isToBeBanned) {
        logger.info("chapter comment: update is to be banned... id: " + chapterCommentId + " isTobeBanned: " + isToBeBanned);
        String sql = "UPDATE chapter_comment SET is_banned = ? WHERE comment_id = ?";
        jdbcTemplate.update(sql, isToBeBanned, chapterCommentId);
    }


    public void updateIsChecked(String chapterCommentId, boolean isChecked) {
        logger.info("chapter comment: update is checked... id: " + chapterCommentId + " isChecked: " + isChecked);
        String sql = "UPDATE chapter_comment SET isChecked = ? WHERE comment_id = ?";
        jdbcTemplate.update(sql, isChecked, chapterCommentId);
    }

    //R
    public ChapterComment getChapterCommentById(String chapterCommentId) {
        logger.info("chapter comment: get chapter comment by comment id... id: " + chapterCommentId);
        String sql = "SELECT * FROM chapter_comment WHERE comment_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, chapterCommentRowMapper, chapterCommentId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ChapterComment> getChapterCommentsByChapterId(String chapterId) {
        logger.info("chapter comment: get chapter comments by chapter id... id: " + chapterId);
        String sql = "SELECT * FROM chapter_comment WHERE chapter_id=? AND is_banned = FALSE ";
        try {
            return jdbcTemplate.query(sql, chapterCommentRowMapper, chapterId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get several chapter comments that need to be checked
     *
     * @param startPosition the start position
     * @param number        the number of the chapter comments to be got
     * @return the result as a list
     */
    public List<ChapterComment> getSeveralCommentsUnchecked(int startPosition, int number) {
        logger.info("chapter comment: get several chapter comments that need to be checked...");
        String sql = "SELECT * FROM chapter_comment WHERE isChecked = FALSE ORDER BY comment_time LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, chapterCommentRowMapper, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get the number of the chapter comments that need to be checked
     *
     * @return the number
     */
    public int getNumberOfCommentsUnchecked() {
        logger.info("chapter comment: get the number of the chapter comments that need to be checked...");
        String sql = "SELECT COUNT(*) FROM chapter_comment WHERE isChecked = FALSE";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * get the comment number of the chapter
     *
     * @param chapterId the id of the chapter
     * @return the number if the chapter
     */
    public int getChapterCommentNumberByChapterId(String chapterId) {
        logger.info("chapter comment: get the comment number of the chapter...");
        String sql = "SELECT COUNT(*) FROM chapter_comment WHERE chapter_id=? AND is_banned = FALSE ";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, chapterId);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * get several comments by the user id
     *
     * @param commentUserId the user id
     * @param startPosition the start position
     * @param number        the number of the comments
     * @return the list that contains the comments as the result
     */
    public List<ChapterComment> getSeveralChapterCommentsByUserId(String commentUserId, int startPosition, int number) {
        logger.info("chapter comment: get several comments by the user id...");
        String sql = "SELECT * FROM chapter_comment WHERE comment_user_id=? ORDER BY comment_time DESC LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, chapterCommentRowMapper, commentUserId, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get the number of the chapter comments by the user id
     *
     * @param commentUserId user id
     * @return the list that contains the comments as the result
     */
    public int getNumberOfChapterCommentsByUserId(String commentUserId) {
        logger.info("chapter comment: get the number of the chapter comments by the user id...");
        String sql = "SELECT COUNT(*) FROM chapter_comment WHERE comment_user_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, commentUserId);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * get several comments by the chapter id
     *
     * @param chapterId     the chapter id
     * @param startPosition the start position
     * @param number        the number of the
     * @return the list that contains the comments as the result
     */
    public List<ChapterComment> getSeveralCommentsByChapterId(String chapterId, int startPosition, int number) {
        logger.info("chapter comment: get several comments by the chapter id...");
        String sql = "SELECT * FROM chapter_comment WHERE chapter_id = ? AND is_banned = FALSE ORDER BY comment_time DESC LIMIT ?,?";
        try {
            return jdbcTemplate.query(sql, chapterCommentRowMapper, chapterId, startPosition, number);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * update the chapter comment by id
     *
     * @param chapterComment the chapter comment that contains all of the information
     */
    public void updateChapterCommentById(ChapterComment chapterComment) {
        logger.info("chapter comment: update the chapter comment by id...");
        String sql = "UPDATE chapter_comment SET comment_id = ?, chapter_id = ?,comment_user_id = ?,comment_time = ?,isChecked = ?,is_banned =?, content = ? WHERE comment_id = ?";
        jdbcTemplate.update(sql,
                chapterComment.getCommentId(),
                chapterComment.getChapterId(),
                chapterComment.getCommentUserId(),
                chapterComment.getCommentTime(),
                chapterComment.getIschecked(),
                chapterComment.getIsBanned(),
                chapterComment.getContent(),
                chapterComment.getCommentId());
    }

    /**
     * delete the chapter comment by id
     *
     * @param chapterCommentId the comment id
     */
    public void deleteChapterComment(String chapterCommentId) {
        logger.info("chapter comment: delete the chapter comment by id");
        String sql = "DELETE FROM chapter_comment WHERE comment_id=?";
        jdbcTemplate.update(sql, chapterCommentId);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

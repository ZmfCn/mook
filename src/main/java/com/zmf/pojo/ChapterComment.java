package com.zmf.pojo;


public class ChapterComment {
    /**
     * This field corresponds to the database column chapter_comment.comment_id
     *
     */
    private String commentId;

    /**
     * This field corresponds to the database column chapter_comment.chapter_id
     *
     */
    private String chapterId;

    /**
     * This field corresponds to the database column chapter_comment.comment_user_id
     *
     */
    private String commentUserId;

    /**
     * This field corresponds to the database column chapter_comment.comment_time
     *
     */
    private java.sql.Timestamp commentTime;

    /**
     * This field corresponds to the database column chapter_comment.isChecked
     *
     */
    private boolean ischecked;

    /**
     * This field corresponds to the database column chapter_comment.is_banned
     *
     */
    private boolean isBanned;

    /**
     * This field corresponds to the database column chapter_comment.content
     *
     */
    private String content;

    /**
     * This method returns the value of the database column chapter_comment.comment_id
     *
     * @return the value of chapter_comment.comment_id
     *
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * This method sets the value of the database column chapter_comment.comment_id
     *
     * @param commentId the value for chapter_comment.comment_id
     *
     */
    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    /**
     * This method returns the value of the database column chapter_comment.chapter_id
     *
     * @return the value of chapter_comment.chapter_id
     *
     */
    public String getChapterId() {
        return chapterId;
    }

    /**
     * This method sets the value of the database column chapter_comment.chapter_id
     *
     * @param chapterId the value for chapter_comment.chapter_id
     *
     */
    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }

    /**
     * This method returns the value of the database column chapter_comment.comment_user_id
     *
     * @return the value of chapter_comment.comment_user_id
     *
     */
    public String getCommentUserId() {
        return commentUserId;
    }

    /**
     * This method sets the value of the database column chapter_comment.comment_user_id
     *
     * @param commentUserId the value for chapter_comment.comment_user_id
     *
     */
    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId == null ? null : commentUserId.trim();
    }

    /**
     * This method returns the value of the database column chapter_comment.comment_time
     *
     * @return the value of chapter_comment.comment_time
     *
     */
    public java.sql.Timestamp getCommentTime() {
        return commentTime;
    }

    /**
     * This method sets the value of the database column chapter_comment.comment_time
     *
     * @param commentTime the value for chapter_comment.comment_time
     *
     */
    public void setCommentTime(java.sql.Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * This method returns the value of the database column chapter_comment.isChecked
     *
     * @return the value of chapter_comment.isChecked
     *
     */
    public boolean getIschecked() {
        return ischecked;
    }

    /**
     * This method sets the value of the database column chapter_comment.isChecked
     *
     * @param ischecked the value for chapter_comment.isChecked
     *
     */
    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    /**
     * This method returns the value of the database column chapter_comment.is_banned
     *
     * @return the value of chapter_comment.is_banned
     *
     */
    public boolean getIsBanned() {
        return isBanned;
    }

    /**
     * This method sets the value of the database column chapter_comment.is_banned
     *
     * @param isBanned the value for chapter_comment.is_banned
     *
     */
    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    /**
     * This method returns the value of the database column chapter_comment.content
     *
     * @return the value of chapter_comment.content
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * This method sets the value of the database column chapter_comment.content
     *
     * @param content the value for chapter_comment.content
     *
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
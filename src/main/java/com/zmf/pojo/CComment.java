package com.zmf.pojo;


public class CComment {
    /**
     * This field corresponds to the database column ccomment.ccomment_id
     *
     */
    private String ccommentId;

    /**
     * This field corresponds to the database column ccomment.commented_comment_id
     *
     */
    private String commentedCommentId;

    /**
     * This field corresponds to the database column ccomment.ccomment_user_id
     *
     */
    private String ccommentUserId;

    /**
     * This field corresponds to the database column ccomment.ccomment_time
     *
     */
    private java.sql.Timestamp ccommentTime;

    /**
     * This field corresponds to the database column ccomment.is_checked
     *
     */
    private boolean isChecked;

    /**
     * This field corresponds to the database column ccomment.is_banned
     *
     */
    private boolean isBanned;

    /**
     * This method returns the value of the database column ccomment.ccomment_id
     *
     * @return the value of ccomment.ccomment_id
     *
     */
    public String getCcommentId() {
        return ccommentId;
    }

    /**
     * This method sets the value of the database column ccomment.ccomment_id
     *
     * @param ccommentId the value for ccomment.ccomment_id
     *
     */
    public void setCcommentId(String ccommentId) {
        this.ccommentId = ccommentId == null ? null : ccommentId.trim();
    }

    /**
     * This method returns the value of the database column ccomment.commented_comment_id
     *
     * @return the value of ccomment.commented_comment_id
     *
     */
    public String getCommentedCommentId() {
        return commentedCommentId;
    }

    /**
     * This method sets the value of the database column ccomment.commented_comment_id
     *
     * @param commentedCommentId the value for ccomment.commented_comment_id
     *
     */
    public void setCommentedCommentId(String commentedCommentId) {
        this.commentedCommentId = commentedCommentId == null ? null : commentedCommentId.trim();
    }

    /**
     * This method returns the value of the database column ccomment.ccomment_user_id
     *
     * @return the value of ccomment.ccomment_user_id
     *
     */
    public String getCcommentUserId() {
        return ccommentUserId;
    }

    /**
     * This method sets the value of the database column ccomment.ccomment_user_id
     *
     * @param ccommentUserId the value for ccomment.ccomment_user_id
     *
     */
    public void setCcommentUserId(String ccommentUserId) {
        this.ccommentUserId = ccommentUserId == null ? null : ccommentUserId.trim();
    }

    /**
     * This method returns the value of the database column ccomment.ccomment_time
     *
     * @return the value of ccomment.ccomment_time
     *
     */
    public java.sql.Timestamp getCcommentTime() {
        return ccommentTime;
    }

    /**
     * This method sets the value of the database column ccomment.ccomment_time
     *
     * @param ccommentTime the value for ccomment.ccomment_time
     *
     */
    public void setCcommentTime(java.sql.Timestamp ccommentTime) {
        this.ccommentTime = ccommentTime;
    }

    /**
     * This method returns the value of the database column ccomment.is_checked
     *
     * @return the value of ccomment.is_checked
     *
     */
    public boolean getIsChecked() {
        return isChecked;
    }

    /**
     * This method sets the value of the database column ccomment.is_checked
     *
     * @param isChecked the value for ccomment.is_checked
     *
     */
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * This method returns the value of the database column ccomment.is_banned
     *
     * @return the value of ccomment.is_banned
     *
     */
    public boolean getIsBanned() {
        return isBanned;
    }

    /**
     * This method sets the value of the database column ccomment.is_banned
     *
     * @param isBanned the value for ccomment.is_banned
     *
     */
    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }
}
package com.zmf.pojo;


public class ChapterLearningRecord {
    /**
     * This field corresponds to the database column chapter_learning_record.record_id
     *
     */
    private String recordId;

    /**
     * This field corresponds to the database column chapter_learning_record.user_id
     *
     */
    private String userId;

    /**
     * This field corresponds to the database column chapter_learning_record.chapter_id
     *
     */
    private String chapterId;

    /**
     * This field corresponds to the database column chapter_learning_record.last_learn
     *
     */
    private java.sql.Timestamp lastLearn;

    /**
     * This method returns the value of the database column chapter_learning_record.record_id
     *
     * @return the value of chapter_learning_record.record_id
     *
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * This method sets the value of the database column chapter_learning_record.record_id
     *
     * @param recordId the value for chapter_learning_record.record_id
     *
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     * This method returns the value of the database column chapter_learning_record.user_id
     *
     * @return the value of chapter_learning_record.user_id
     *
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets the value of the database column chapter_learning_record.user_id
     *
     * @param userId the value for chapter_learning_record.user_id
     *
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method returns the value of the database column chapter_learning_record.chapter_id
     *
     * @return the value of chapter_learning_record.chapter_id
     *
     */
    public String getChapterId() {
        return chapterId;
    }

    /**
     * This method sets the value of the database column chapter_learning_record.chapter_id
     *
     * @param chapterId the value for chapter_learning_record.chapter_id
     *
     */
    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }

    /**
     * This method returns the value of the database column chapter_learning_record.last_learn
     *
     * @return the value of chapter_learning_record.last_learn
     *
     */
    public java.sql.Timestamp getLastLearn() {
        return lastLearn;
    }

    /**
     * This method sets the value of the database column chapter_learning_record.last_learn
     *
     * @param lastLearn the value for chapter_learning_record.last_learn
     *
     */
    public void setLastLearn(java.sql.Timestamp lastLearn) {
        this.lastLearn = lastLearn;
    }
}
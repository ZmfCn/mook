package com.zmf.pojo;


public class CourseLearningRecord {
    /**
     * This field corresponds to the database column course_learning_record.record_id
     *
     */
    private String recordId;

    /**
     * This field corresponds to the database column course_learning_record.course_id
     *
     */
    private String courseId;

    /**
     * This field corresponds to the database column course_learning_record.user_id
     *
     */
    private String userId;

    /**
     * This field corresponds to the database column course_learning_record.start_time
     *
     */
    private java.sql.Timestamp startTime;

    /**
     * This method returns the value of the database column course_learning_record.record_id
     *
     * @return the value of course_learning_record.record_id
     *
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * This method sets the value of the database column course_learning_record.record_id
     *
     * @param recordId the value for course_learning_record.record_id
     *
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     * This method returns the value of the database column course_learning_record.course_id
     *
     * @return the value of course_learning_record.course_id
     *
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method sets the value of the database column course_learning_record.course_id
     *
     * @param courseId the value for course_learning_record.course_id
     *
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method returns the value of the database column course_learning_record.user_id
     *
     * @return the value of course_learning_record.user_id
     *
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets the value of the database column course_learning_record.user_id
     *
     * @param userId the value for course_learning_record.user_id
     *
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method returns the value of the database column course_learning_record.start_time
     *
     * @return the value of course_learning_record.start_time
     *
     */
    public java.sql.Timestamp getStartTime() {
        return startTime;
    }

    /**
     * This method sets the value of the database column course_learning_record.start_time
     *
     * @param startTime the value for course_learning_record.start_time
     *
     */
    public void setStartTime(java.sql.Timestamp startTime) {
        this.startTime = startTime;
    }
}
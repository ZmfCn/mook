package com.zmf.pojo;

public class Chapter {
    /**
     * This field corresponds to the database column chapter.chapter_id
     *
     */
    private String chapterId;

    /**
     * This field corresponds to the database column chapter.owner_course_id
     *
     */
    private String ownerCourseId;

    /**
     * This field corresponds to the database column chapter.chapter_title
     *
     */
    private String chapterTitle;

    /**
     * This field corresponds to the database column chapter.is_pdf
     *
     */
    private boolean isPdf;

    /**
     * This field corresponds to the database column chapter.filepath
     *
     */
    private String filepath;

    /**
     * This field corresponds to the database column chapter.duration
     *
     */
    private String duration;

    /**
     * This field corresponds to the database column chapter.chapter_order
     *
     */
    private int chapterOrder;

    /**
     * This method returns the value of the database column chapter.chapter_id
     *
     * @return the value of chapter.chapter_id
     *
     */
    public String getChapterId() {
        return chapterId;
    }

    /**
     * This method sets the value of the database column chapter.chapter_id
     *
     * @param chapterId the value for chapter.chapter_id
     *
     */
    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }

    /**
     * This method returns the value of the database column chapter.owner_course_id
     *
     * @return the value of chapter.owner_course_id
     *
     */
    public String getOwnerCourseId() {
        return ownerCourseId;
    }

    /**
     * This method sets the value of the database column chapter.owner_course_id
     *
     * @param ownerCourseId the value for chapter.owner_course_id
     *
     */
    public void setOwnerCourseId(String ownerCourseId) {
        this.ownerCourseId = ownerCourseId == null ? null : ownerCourseId.trim();
    }

    /**
     * This method returns the value of the database column chapter.chapter_title
     *
     * @return the value of chapter.chapter_title
     *
     */
    public String getChapterTitle() {
        return chapterTitle;
    }

    /**
     * This method sets the value of the database column chapter.chapter_title
     *
     * @param chapterTitle the value for chapter.chapter_title
     *
     */
    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle == null ? null : chapterTitle.trim();
    }

    /**
     * This method returns the value of the database column chapter.is_pdf
     *
     * @return the value of chapter.is_pdf
     *
     */
    public boolean getIsPdf() {
        return isPdf;
    }

    /**
     * This method sets the value of the database column chapter.is_pdf
     *
     * @param isPdf the value for chapter.is_pdf
     *
     */
    public void setIsPdf(boolean isPdf) {
        this.isPdf = isPdf;
    }

    /**
     * This method returns the value of the database column chapter.filepath
     *
     * @return the value of chapter.filepath
     *
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * This method sets the value of the database column chapter.filepath
     *
     * @param filepath the value for chapter.filepath
     *
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    /**
     * This method returns the value of the database column chapter.duration
     *
     * @return the value of chapter.duration
     *
     */
    public String getDuration() {
        return duration;
    }

    /**
     * This method sets the value of the database column chapter.duration
     *
     * @param duration the value for chapter.duration
     *
     */
    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    /**
     * This method returns the value of the database column chapter.chapter_order
     *
     * @return the value of chapter.chapter_order
     *
     */
    public int getChapterOrder() {
        return chapterOrder;
    }

    /**
     * This method sets the value of the database column chapter.chapter_order
     *
     * @param chapterOrder the value for chapter.chapter_order
     *
     */
    public void setChapterOrder(int chapterOrder) {
        this.chapterOrder = chapterOrder;
    }
}
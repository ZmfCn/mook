package com.zmf.pojo;

public class Course {
    /**
     * This field corresponds to the database column course.course_id
     *
     */
    private String courseId;

    /**
     * This field corresponds to the database column course.course_type_id
     *
     */
    private String courseTypeId;

    /**
     * This field corresponds to the database column course.teacher_name
     *
     */
    private String teacherName;

    /**
     * This field corresponds to the database column course.charpter_number
     *
     */
    private short charpterNumber;

    /**
     * This field corresponds to the database column course.image_path
     *
     */
    private String imagePath;

    /**
     * This field corresponds to the database column course.course_path
     *
     */
    private String coursePath;

    /**
     * This field corresponds to the database column course.course_name
     *
     */
    private String courseName;

    /**
     * This field corresponds to the database column course.course_count
     *
     */
    private int courseCount;

    /**
     * This method returns the value of the database column course.course_id
     *
     * @return the value of course.course_id
     *
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method sets the value of the database column course.course_id
     *
     * @param courseId the value for course.course_id
     *
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method returns the value of the database column course.course_type_id
     *
     * @return the value of course.course_type_id
     *
     */
    public String getCourseTypeId() {
        return courseTypeId;
    }

    /**
     * This method sets the value of the database column course.course_type_id
     *
     * @param courseTypeId the value for course.course_type_id
     *
     */
    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId == null ? null : courseTypeId.trim();
    }

    /**
     * This method returns the value of the database column course.teacher_name
     *
     * @return the value of course.teacher_name
     *
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * This method sets the value of the database column course.teacher_name
     *
     * @param teacherName the value for course.teacher_name
     *
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    /**
     * This method returns the value of the database column course.charpter_number
     *
     * @return the value of course.charpter_number
     *
     */
    public short getCharpterNumber() {
        return charpterNumber;
    }

    /**
     * This method sets the value of the database column course.charpter_number
     *
     * @param charpterNumber the value for course.charpter_number
     *
     */
    public void setCharpterNumber(short charpterNumber) {
        this.charpterNumber = charpterNumber;
    }

    /**
     * This method returns the value of the database column course.image_path
     *
     * @return the value of course.image_path
     *
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * This method sets the value of the database column course.image_path
     *
     * @param imagePath the value for course.image_path
     *
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    /**
     * This method returns the value of the database column course.course_path
     *
     * @return the value of course.course_path
     *
     */
    public String getCoursePath() {
        return coursePath;
    }

    /**
     * This method sets the value of the database column course.course_path
     *
     * @param coursePath the value for course.course_path
     *
     */
    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath == null ? null : coursePath.trim();
    }

    /**
     * This method returns the value of the database column course.course_name
     *
     * @return the value of course.course_name
     *
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This method sets the value of the database column course.course_name
     *
     * @param courseName the value for course.course_name
     *
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    /**
     * This method returns the value of the database column course.course_count
     *
     * @return the value of course.course_count
     *
     */
    public int getCourseCount() {
        return courseCount;
    }

    /**
     * This method sets the value of the database column course.course_count
     *
     * @param courseCount the value for course.course_count
     *
     */
    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }
}
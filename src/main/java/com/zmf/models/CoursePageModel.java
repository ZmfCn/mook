package com.zmf.models;

import com.zmf.pojo.Chapter;
import com.zmf.pojo.Course;

import java.util.List;

/**
 * @author zmf
 * @description the page model of the course page
 * @timestamp 2017-12-09 10:50
 */
public class CoursePageModel {
    /**
     * the course content of the course page
     */
    private Course course;
    /**
     * the chapters of this course
     */
    private List<Chapter> chapters;
    /**
     * whether the user has added this course to learn
     */
    private boolean isUserAddedToLearn;

    public CoursePageModel(Course course, List<Chapter> chapters) {
        this.course = course;
        this.chapters = chapters;
    }

    public CoursePageModel() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public boolean isUserAddedToLearn() {
        return isUserAddedToLearn;
    }

    public void setUserAddedToLearn(boolean userAddedToLearn) {
        isUserAddedToLearn = userAddedToLearn;
    }
}

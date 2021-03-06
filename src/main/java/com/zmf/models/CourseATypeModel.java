package com.zmf.models;

import com.zmf.pojo.Course;

import java.util.List;

/**
 * @author zmf
 * @description the model that contains the data of the courses of a type
 * @timestamp 2017-12-08 21:48
 */
public class CourseATypeModel {
    /**
     * courses in the page
     */
    private List<Course> courses;
    /**
     * stores the number of the pages
     */
    private int pages;
    /**
     * indicate the current course page indexing from 0
     */
    private int currentPage;
    /**
     * the id of the current course type id
     */
    private String courseTypeId;
    /**
     * the type name of all courses
     */
    private String pageTypeName;
    /**
     * the number of courses in a page
     */
    public static final int PAGE_COURSE_NUMBER = 4;

    public CourseATypeModel(List<Course> courses, int pages, int currentPage, String courseTypeId) {
        this.courses = courses;
        this.pages = pages;
        this.currentPage = currentPage;
        this.courseTypeId = courseTypeId;
    }

    public CourseATypeModel() {

    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getPageTypeName() {
        return pageTypeName;
    }

    public void setPageTypeName(String pageTypeName) {
        this.pageTypeName = pageTypeName;
    }
}

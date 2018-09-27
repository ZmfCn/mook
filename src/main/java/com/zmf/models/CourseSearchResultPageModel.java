package com.zmf.models;

import com.zmf.pojo.Course;

import java.util.List;

/**
 * @author zmf
 * @description the model that contains the data of all the courses
 * @timestamp 2017-12-08 21:48
 */
public class CourseSearchResultPageModel {
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

    private String string;
    /**
     * the number of courses in a page
     */
    public static final int PAGE_COURSE_NUMBER = 12;

    public CourseSearchResultPageModel(List<Course> courses, int pages, int currentPage, String string) {
        this.courses = courses;
        this.pages = pages;
        this.currentPage = currentPage;
        this.string = string;
    }

    public CourseSearchResultPageModel() {

    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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
}

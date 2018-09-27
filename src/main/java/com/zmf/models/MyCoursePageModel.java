package com.zmf.models;

import com.zmf.pojo.Course;

import java.util.List;

/**
 * @author zmf
 * @description the model that contains the data of the my course page
 * @timestamp 2017-12-08 09:35
 */
public class MyCoursePageModel {
    /**
     * courses in the page
     */
    private List<Course> courses;
    /**
     * stores the number of the pages
     */
    private int pages;
    /**
     * indicate the current course page
     */
    private int currentPage;
    /**
     * the number of courses in a page
     */
    public static final int PAGE_COURSE_NUMBER = 12;

    public MyCoursePageModel() {

    }

    public MyCoursePageModel(List<Course> courses, int pages, int currentPage) {
        this.courses = courses;
        this.pages = pages;
        this.currentPage = currentPage;
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

    public class Course {
        /**
         * the date like yyyy-mm-dd
         */
        private String date;
        private com.zmf.pojo.Course course;

        public Course(String date, com.zmf.pojo.Course course) {
            this.date = date;
            this.course = course;
        }

        public Course() {

        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public com.zmf.pojo.Course getCourse() {
            return course;
        }

        public void setCourse(com.zmf.pojo.Course course) {
            this.course = course;
        }
    }
}

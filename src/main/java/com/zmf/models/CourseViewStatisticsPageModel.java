package com.zmf.models;

import com.zmf.pojo.TopType;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-12 19:03
 */
public class CourseViewStatisticsPageModel {
    private List<Course> courses;

    public CourseViewStatisticsPageModel(List<Course> courses) {
        this.courses = courses;
    }

    public CourseViewStatisticsPageModel() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public class Course {
        private com.zmf.pojo.Course content;
        private com.zmf.pojo.TopType topType;

        public Course(com.zmf.pojo.Course content, TopType topType) {
            this.content = content;
            this.topType = topType;
        }

        public Course() {
        }

        public com.zmf.pojo.Course getContent() {
            return content;
        }

        public void setContent(com.zmf.pojo.Course content) {
            this.content = content;
        }

        public TopType getTopType() {
            return topType;
        }

        public void setTopType(TopType topType) {
            this.topType = topType;
        }
    }
}

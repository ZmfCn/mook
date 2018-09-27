package com.zmf.models;

import com.zmf.pojo.CourseType;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 22:07
 */
public class AddCoursePageModel {
    private List<CourseType> courseTypes;

    public AddCoursePageModel(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public AddCoursePageModel() {

    }

    public List<CourseType> getCourseTypes() {
        return courseTypes;
    }

    public void setCourseTypes(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }
}

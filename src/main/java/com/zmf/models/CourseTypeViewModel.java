package com.zmf.models;

import java.util.List;

/**
 * @author zmf
 * @description the model contains data that is in course type view page of course manager
 * @timestamp 2017-12-09 22:23
 */
public class CourseTypeViewModel {
    List<CourseType> courseTypes;

    public CourseTypeViewModel(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public CourseTypeViewModel() {

    }

    public List<CourseType> getCourseTypes() {
        return courseTypes;
    }

    public void setCourseTypes(List<CourseType> courseTypes) {
        this.courseTypes = courseTypes;
    }

    public class CourseType {
        private com.zmf.pojo.CourseType courseType;
        private int subCourseNumber;
        private String topTypeName;

        public CourseType(com.zmf.pojo.CourseType courseType, int subCourseNumber, String topTypeName) {
            this.courseType = courseType;
            this.subCourseNumber = subCourseNumber;
            this.topTypeName = topTypeName;
        }

        public CourseType() {

        }

        public com.zmf.pojo.CourseType getCourseType() {
            return courseType;
        }

        public void setCourseType(com.zmf.pojo.CourseType courseType) {
            this.courseType = courseType;
        }

        public int getSubCourseNumber() {
            return subCourseNumber;
        }

        public void setSubCourseNumber(int subCourseNumber) {
            this.subCourseNumber = subCourseNumber;
        }

        public String getTopTypeName() {
            return topTypeName;
        }

        public void setTopTypeName(String topTypeName) {
            this.topTypeName = topTypeName;
        }
    }
}

package com.zmf.models;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-12 18:28
 */
public class CourseTypeStatisticsPageModel {
    List<TopType> topTypes;

    public CourseTypeStatisticsPageModel(List<TopType> topTypes) {
        this.topTypes = topTypes;
    }

    public CourseTypeStatisticsPageModel() {
    }

    public List<TopType> getTopTypes() {
        return topTypes;
    }

    public void setTopTypes(List<TopType> topTypes) {
        this.topTypes = topTypes;
    }

    public class TopType {
        private com.zmf.pojo.TopType content;
        private int courseNumber;

        public TopType(com.zmf.pojo.TopType content, int courseNumber) {
            this.content = content;
            this.courseNumber = courseNumber;
        }

        public TopType() {
        }

        public com.zmf.pojo.TopType getContent() {
            return content;
        }

        public void setContent(com.zmf.pojo.TopType content) {
            this.content = content;
        }

        public int getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
        }
    }
}

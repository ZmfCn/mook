package com.zmf.models;

import com.zmf.pojo.Course;
import com.zmf.pojo.TopType;

import java.util.List;

/**
 * the class to put the data that is needed in the index page
 */
public class IndexPageModel {
    /**
     * the top types of all contents
     */
    private List<TopType> topTypes;
    /**
     * the recommend courses;
     */
    private List<Course> recommendCourses;
    /**
     * the new courses that new occur
     */
    private List<Course> newCourses;
    /**
     * the improve courses
     */
    private List<Course> improveCourses;


    public IndexPageModel() {
    }

    public IndexPageModel(List<TopType> topTypes, List<Course> recommandCourses, List<Course> newCourses, List<Course> improveCourses) {
        this.topTypes = topTypes;
        this.recommendCourses = recommandCourses;
        this.newCourses = newCourses;
        this.improveCourses = improveCourses;
    }

    public List<TopType> getTopTypes() {
        return topTypes;
    }

    public void setTopTypes(List<TopType> topTypes) {
        this.topTypes = topTypes;
    }

    public List<Course> getRecommendCourses() {
        return recommendCourses;
    }

    public void setRecommendCourses(List<Course> recommendCourses) {
        this.recommendCourses = recommendCourses;
    }

    public List<Course> getNewCourses() {
        return newCourses;
    }

    public void setNewCourses(List<Course> newCourses) {
        this.newCourses = newCourses;
    }

    public List<Course> getImproveCourses() {
        return improveCourses;
    }

    public void setImproveCourses(List<Course> improveCourses) {
        this.improveCourses = improveCourses;
    }


    public class TopType {
        /**
         * the content of current top type
         */
        private com.zmf.pojo.TopType content;
        /**
         * the list of the course types that belong to this top type
         */
        private List<com.zmf.pojo.CourseType> courseTypes;

        public TopType(com.zmf.pojo.TopType content, List<com.zmf.pojo.CourseType> courseTypes) {
            this.content = content;
            this.courseTypes = courseTypes;
        }

        public TopType() {

        }

        public com.zmf.pojo.TopType getContent() {
            return content;
        }

        public void setContent(com.zmf.pojo.TopType content) {
            this.content = content;
        }

        public List<com.zmf.pojo.CourseType> getCourseTypes() {
            return courseTypes;
        }

        public void setCourseTypes(List<com.zmf.pojo.CourseType> courseTypes) {
            this.courseTypes = courseTypes;
        }
    }
}

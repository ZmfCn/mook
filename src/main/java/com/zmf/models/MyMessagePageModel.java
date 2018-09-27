package com.zmf.models;

import com.zmf.pojo.Chapter;
import com.zmf.pojo.ChapterComment;
import com.zmf.pojo.Course;

import java.util.List;

/**
 * @author zmf
 * @description the model that contains data of my message page
 * @timestamp 2017-12-08 21:43
 */
public class MyMessagePageModel {
    /**
     * the max number of the comment in one page
     */
    public static final int MAX_COMMENT_NUMBER = 6;
    /**
     * the comment that comments the chapter directly
     */
    private List<MyMessagePageModel.Comment> comments;
    private int currentPage;
    private int totalPage;

    public MyMessagePageModel(List<Comment> comments, int currentPage, int totalPage) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public MyMessagePageModel() {
    }


    public List<MyMessagePageModel.Comment> getComments() {
        return comments;
    }

    public void setComments(List<MyMessagePageModel.Comment> comments) {
        this.comments = comments;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public class Comment {
        com.zmf.pojo.ChapterComment chapterComment;
        String userName;
        String time;
        private Course course;
        private Chapter chapter;

        public Comment(ChapterComment chapterComment, String userName, String time, Course course, Chapter chapter) {
            this.chapterComment = chapterComment;
            this.userName = userName;
            this.time = time;
            this.course = course;
            this.chapter = chapter;
        }

        public Comment() {
        }

        public ChapterComment getChapterComment() {
            return chapterComment;
        }

        public void setChapterComment(ChapterComment chapterComment) {
            this.chapterComment = chapterComment;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Chapter getChapter() {
            return chapter;
        }

        public void setChapter(Chapter chapter) {
            this.chapter = chapter;
        }
    }
}

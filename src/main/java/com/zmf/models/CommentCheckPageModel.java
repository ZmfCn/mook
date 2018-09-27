package com.zmf.models;

import com.zmf.pojo.ChapterComment;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 16:05
 */
public class CommentCheckPageModel {
    /**
     * the max number of the comment in one page
     */
    public static final int MAX_COMMENT_NUMBER = 5;
    /**
     * the comment that comments the chapter directly
     */
    private List<CommentCheckPageModel.Comment> comments;
    private int currentPage;
    private int totalPage;


    public CommentCheckPageModel(List<Comment> comments, int currentPage, int totalPage) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public CommentCheckPageModel() {
    }


    public List<CommentCheckPageModel.Comment> getComments() {
        return comments;
    }

    public void setComments(List<CommentCheckPageModel.Comment> comments) {
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

        public Comment(ChapterComment chapterComment, String userName, String time) {
            this.chapterComment = chapterComment;
            this.userName = userName;
            this.time = time;
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
    }
}

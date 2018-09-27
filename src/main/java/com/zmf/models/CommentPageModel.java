package com.zmf.models;

import com.zmf.pojo.ChapterComment;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-15 21:45
 */
public class CommentPageModel {
    /**
     * the max number of the comment in one page
     */
    public static final int MAX_COMMENT_NUMBER = 4;
    /**
     * the comment that comments the chapter directly
     */
    private List<Comment> comments;
    private int currentPage;
    private int totalPage;
    private String chapterId;


    public CommentPageModel(List<Comment> comments, int currentPage, int totalPage, String chapterId) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.chapterId = chapterId;
    }

    public CommentPageModel() {
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
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

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
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

//    public class CComment {
//        com.zmf.pojo.CComment cComment;
//        String userName;
//        List<CComment> cComments;
//
//        public CComment(com.zmf.pojo.CComment cComment, String userName, List<CComment> cComments) {
//            this.cComment = cComment;
//            this.userName = userName;
//            this.cComments = cComments;
//        }
//
//        public CComment() {
//        }
//
//        public com.zmf.pojo.CComment getcComment() {
//            return cComment;
//        }
//
//        public void setcComment(com.zmf.pojo.CComment cComment) {
//            this.cComment = cComment;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public List<CComment> getcComments() {
//            return cComments;
//        }
//
//        public void setcComments(List<CComment> cComments) {
//            this.cComments = cComments;
//        }
//    }
}

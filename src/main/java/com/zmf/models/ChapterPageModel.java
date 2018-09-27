package com.zmf.models;

import com.zmf.pojo.Chapter;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-14 16:08
 */
public class ChapterPageModel {
    private String encryptedNextChapterId;
    private Chapter chapter;
    private String courseName;

    public ChapterPageModel(String encryptedNextChapterId, Chapter chapter, String courseName) {
        this.encryptedNextChapterId = encryptedNextChapterId;
        this.chapter = chapter;
        this.courseName = courseName;
    }

    public ChapterPageModel() {
    }

    public String getEncryptedNextChapterId() {
        return encryptedNextChapterId;
    }

    public void setEncryptedNextChapterId(String encryptedNextChapterId) {
        this.encryptedNextChapterId = encryptedNextChapterId;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

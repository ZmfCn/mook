package com.zmf.modelProvider;

import com.zmf.Utils.Util;
import com.zmf.dao.ChapterCommentDao;
import com.zmf.dao.ChapterDao;
import com.zmf.dao.CourseDao;
import com.zmf.models.MyMessagePageModel;
import com.zmf.pojo.Chapter;
import com.zmf.pojo.ChapterComment;
import com.zmf.pojo.Course;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 19:10
 */
@Component
public class MyMessagePageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private ChapterCommentDao chapterCommentDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ChapterDao chapterDao;

    public MyMessagePageModel getModel(String userid, int pageNumber) {
        MyMessagePageModel model = new MyMessagePageModel();
        model = setPageNumber(model, userid);
        model.setCurrentPage(pageNumber);
        return setComments(model, userid, pageNumber);
    }

    private MyMessagePageModel setComments(MyMessagePageModel model, String userId, int pageNumber) {
        List<ChapterComment> chapterComments = chapterCommentDao.getSeveralChapterCommentsByUserId(userId,
                getStartPosition(pageNumber), MyMessagePageModel.MAX_COMMENT_NUMBER);
        int size = chapterComments.size();

        List<MyMessagePageModel.Comment> comments = new ArrayList<>();
        MyMessagePageModel.Comment comment;
        ChapterComment temp;
        for (int i = 0; i < size; i++) {
            comment = model.new Comment();
            temp = chapterComments.get(i);
            comment = setCourse(comment, temp.getCommentId());
            comment = setChapter(comment, temp.getCommentId());
            temp.setCommentId(encryptionService.encrypt(temp.getCommentId()));
            temp.setChapterId(encryptionService.encrypt(temp.getChapterId()));
            temp.setCommentUserId(encryptionService.encrypt(temp.getCommentUserId()));
            comment.setChapterComment(temp);
            comment.setTime(Util.getTimeString(temp.getCommentTime()));
            comments.add(comment);
        }

        model.setComments(comments);
        return model;
    }

    private MyMessagePageModel.Comment setChapter(MyMessagePageModel.Comment comment, String commentId) {
        Chapter chapter = chapterDao.getChapterByCommentId(commentId);
        chapter.setChapterId(encryptionService.encrypt(chapter.getChapterId()));
        chapter.setOwnerCourseId(encryptionService.encrypt(chapter.getOwnerCourseId()));
        comment.setChapter(chapter);
        return comment;
    }

    private MyMessagePageModel.Comment setCourse(MyMessagePageModel.Comment comment, String commentId) {
        Course course = courseDao.getCourseByCommentId(commentId);
        course.setCourseId(encryptionService.encrypt(course.getCourseId()));
        course.setCourseTypeId(encryptionService.encrypt(course.getCourseTypeId()));
        comment.setCourse(course);
        return comment;
    }

    private MyMessagePageModel setPageNumber(MyMessagePageModel model, String userId) {
        int recordNumber = chapterCommentDao.getNumberOfChapterCommentsByUserId(userId);
        model.setTotalPage((int) Math.ceil(recordNumber * 1.0 / MyMessagePageModel.MAX_COMMENT_NUMBER));
        return model;
    }

    private int getStartPosition(int pageNumber) {
        return MyMessagePageModel.MAX_COMMENT_NUMBER * pageNumber;
    }
}

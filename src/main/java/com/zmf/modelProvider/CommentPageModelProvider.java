package com.zmf.modelProvider;

import com.zmf.Utils.Util;
import com.zmf.dao.ChapterCommentDao;
import com.zmf.dao.UserDao;
import com.zmf.models.CommentPageModel;
import com.zmf.pojo.ChapterComment;
import com.zmf.pojo.User;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-15 22:00
 */
@Component
public class CommentPageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ChapterCommentDao chapterCommentDao;

    public CommentPageModel getModel(String chapterId, int pageNumber) {
        CommentPageModel model = new CommentPageModel();
        model = setPageNumber(model, chapterId);
        model.setCurrentPage(pageNumber);
        model.setChapterId(encryptionService.encrypt(chapterId));
        return setComments(model, chapterId, pageNumber);
    }

    private CommentPageModel setComments(CommentPageModel model, String chapterId, int pageNumber) {
        List<ChapterComment> chapterComments = chapterCommentDao.getSeveralCommentsByChapterId(chapterId,
                getStartPosition(pageNumber), CommentPageModel.MAX_COMMENT_NUMBER);
        int size = chapterComments.size();

        List<CommentPageModel.Comment> comments = new ArrayList<>();
        CommentPageModel.Comment comment;
        ChapterComment temp;
        for (int i = 0; i < size; i++) {
            comment = model.new Comment();
            temp = chapterComments.get(i);
            comment.setUserName(getUserName(temp.getCommentUserId()));
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



    private String getUserName(String userId) {
        User user = userDao.getUserById(userId);
        return user.getUserName();
    }


    private CommentPageModel setPageNumber(CommentPageModel model, String chapterId) {
        int recordNumber = chapterCommentDao.getChapterCommentNumberByChapterId(chapterId);
        model.setTotalPage((int) Math.ceil(recordNumber * 1.0 / CommentPageModel.MAX_COMMENT_NUMBER));
        return model;
    }

    private int getStartPosition(int pageNumber) {
        return CommentPageModel.MAX_COMMENT_NUMBER * pageNumber;
    }
}

package com.zmf.modelProvider;

import com.zmf.Utils.Util;
import com.zmf.dao.ChapterCommentDao;
import com.zmf.dao.UserDao;
import com.zmf.models.CommentCheckPageModel;
import com.zmf.pojo.ChapterComment;
import com.zmf.pojo.User;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 16:08
 */
@Component
public class CommentCheckPageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ChapterCommentDao chapterCommentDao;

    public CommentCheckPageModel getModel(int pageNumber) {
        CommentCheckPageModel model = new CommentCheckPageModel();
        model = setPageNumber(model);
        model.setCurrentPage(pageNumber);
        return setComments(model, pageNumber);
    }

    private CommentCheckPageModel setComments(CommentCheckPageModel model, int pageNumber) {
        List<ChapterComment> chapterComments = chapterCommentDao.getSeveralCommentsUnchecked(
                getStartPosition(pageNumber), CommentCheckPageModel.MAX_COMMENT_NUMBER);
        int size = chapterComments.size();

        List<CommentCheckPageModel.Comment> comments = new ArrayList<>();
        CommentCheckPageModel.Comment comment;
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

    private CommentCheckPageModel setPageNumber(CommentCheckPageModel model) {
        int recordNumber = chapterCommentDao.getNumberOfCommentsUnchecked();
        model.setTotalPage((int) Math.ceil(recordNumber * 1.0 / CommentCheckPageModel.MAX_COMMENT_NUMBER));
        return model;
    }

    private int getStartPosition(int pageNumber) {
        return CommentCheckPageModel.MAX_COMMENT_NUMBER * pageNumber;
    }
}

package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.dao.ChapterCommentDao;
import com.zmf.pojo.ChapterComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-13 00:01
 */
@Component
@SuppressWarnings("all")
@Transactional
public class ChapterCommentService {
    @Autowired
    private ChapterCommentDao chapterCommentDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private KeywordService keywordService;

    /**
     * add a new comment
     *
     * @param userId    the user id
     * @param chapterId the chapter id
     * @param content   the content of the comment
     * @return
     */
    public boolean addChapterComment(String userId, String chapterId, String content) {
        if (!userService.isValidToComment(userId)) {
            return false;
        }
        if (!chapterService.isValid(chapterId)) {
            return false;
        }
        if (Util.isEmpty(content)) {
            return false;
        }
        ChapterComment chapterComment = generateComment(userId, chapterId, content);
        chapterCommentDao.addCourseComment(chapterComment);
        return true;
    }

    public void setCommmentAbandoned(String chapterCommentId, boolean isToBeBanned) {
        if (chapterCommentDao.getChapterCommentById(chapterCommentId) != null) {
            chapterCommentDao.updateIsToBeBanned(chapterCommentId, isToBeBanned);
        }
    }


    public void setCommmentIsChecked(String chapterCommentId, boolean isChecked) {
        if (chapterCommentDao.getChapterCommentById(chapterCommentId) != null) {
            chapterCommentDao.updateIsChecked(chapterCommentId, isChecked);
        }
    }


    public boolean deleteChapterComment(ChapterComment chapterComment, List<String> container, List<String> errorContainer) {
        if (chapterCommentDao.getChapterCommentById(chapterComment.getCommentId()) == null) {
            errorContainer.add("评论不存在,无需删除");
            return false;
        } else {
            // delete ccomment
//            List<CComment> cComments = cCommentDao.getCCommentsByCommentedId(chapterComment.getCommentId());
//            int size = cComments.size();
//            for (int i = 0; i < size; i++) {
//                cCommentService.deleteCComment(cComments.get(i));
//            }
            // delete comment record
            chapterCommentDao.deleteChapterComment(chapterComment.getCommentId());
            container.add("删除成功");
            return true;
        }
    }


    public boolean deleteChapterComment(String chapterCommentId) {
        if (chapterCommentDao.getChapterCommentById(chapterCommentId) == null) {
            return false;
        } else {
            // delete ccomment
//            List<CComment> cComments = cCommentDao.getCCommentsByCommentedId(chapterComment.getCommentId());
//            int size = cComments.size();
//            for (int i = 0; i < size; i++) {
//                cCommentService.deleteCComment(cComments.get(i));
//            }
            // delete comment record
            chapterCommentDao.deleteChapterComment(chapterCommentId);
            return true;
        }
    }

    public boolean deleteChapterComment(ChapterComment chapterComment) {
        if (chapterCommentDao.getChapterCommentById(chapterComment.getCommentId()) == null) {
            return false;
        } else {
            // delete ccomment
//            List<CComment> cComments = cCommentDao.getCCommentsByCommentedId(chapterComment.getCommentId());
//            int size = cComments.size();
//            for (int i = 0; i < size; i++) {
//                cCommentService.deleteCComment(cComments.get(i));
//            }
            // delete comment record
            chapterCommentDao.deleteChapterComment(chapterComment.getCommentId());
            return true;
        }
    }

    public ChapterComment generateComment(String userId, String chapterId, String content) {
        ChapterComment chapterComment = new ChapterComment();
        chapterComment.setContent(content);
        chapterComment.setIsBanned(false);
        chapterComment.setIschecked(!keywordService.isToBeFiltered(content));
        chapterComment.setCommentUserId(userId);
        chapterComment.setChapterId(chapterId);
        long time = System.currentTimeMillis();
        chapterComment.setCommentId(generateCommentId(time));
        chapterComment.setCommentTime(new Timestamp(time));
        return chapterComment;
    }

    public String generateCommentId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("comment");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }
}

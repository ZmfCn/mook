package com.zmf.service;

import com.zmf.dao.CCommentDao;
import com.zmf.pojo.CComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-13 00:01
 */
@Component
@SuppressWarnings("all")
@Transactional
public class CCommentService {
//    @Autowired
//    private CCommentDao cCommentDao;
//
//    public boolean deleteCComment(CComment cComment, List<String> container, List<String> errorContainer) {
//        if (cCommentDao.getCCommentById(cComment.getCcommentId())==null) {
//            errorContainer.add("评论不存在,无需删除");
//            return false;
//        } else {
//            cCommentDao.deleteCComment(cComment.getCcommentId());
//            container.add("删除成功");
//            return true;
//        }
//    }
//
//    public boolean deleteCComment(CComment cComment) {
//        if (cCommentDao.getCCommentById(cComment.getCcommentId())==null) {
//            return false;
//        } else {
//            cCommentDao.deleteCComment(cComment.getCcommentId());
//            return true;
//        }
//    }
}

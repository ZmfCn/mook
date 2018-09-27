package com.zmf.dao;

import com.zmf.mapper.CCommentRowMapper;
import com.zmf.pojo.CComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class CCommentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CCommentRowMapper ccommentRowMapper;

    //C
    public void addCComment(CComment ccomment) {
        String sql = "INSERT INTO ccomment (ccomment_id,commented_comment_id,ccomment_user_id,ccomment_time,is_checked,is_banned) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                ccomment.getCcommentId(),
                ccomment.getCommentedCommentId(),
                ccomment.getCcommentUserId(),
                ccomment.getCcommentTime(),
                ccomment.getIsChecked(),
                ccomment.getIsBanned()
        );
    }

    //R
    public CComment getCCommentById(String ccommentId) {
        String sql = "SELECT * FROM ccomment WHERE ccomment_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, ccommentRowMapper, ccommentId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<CComment> getCCommentsByCommentedId(String ccommentCommentedId) {
        String sql = "SELECT * FROM ccomment WHERE commented_comment_id=?";
        try {
            return jdbcTemplate.query(sql, ccommentRowMapper, ccommentCommentedId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<CComment> getCCommentsByCcommentUserId(String ccommentUserId) {
        String sql = "SELECT * FROM ccomment WHERE ccomment_user_id=?";
        try {
            return jdbcTemplate.query(sql, ccommentRowMapper, ccommentUserId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    //U
    public void updateCCommentById(CComment cComment) {
        String sql = "UPDATE ccomment SET ccomment_id=?,commented_comment_id=?,ccomment_user_id=?,ccomment_time=?,is_checked=?,is_banned=? WHERE ccomment_id = ?";
        jdbcTemplate.update(sql,
                cComment.getCcommentId(),
                cComment.getCommentedCommentId(),
                cComment.getCcommentUserId(),
                cComment.getCcommentTime(),
                cComment.getIsChecked(),
                cComment.getIsBanned(),
                cComment.getCcommentId());
    }

    //D
    public void deleteCComment(String ccommentId) {
        String sql = "DELETE FROM ccomment WHERE ccomment_id=?";
        jdbcTemplate.update(sql, ccommentId);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

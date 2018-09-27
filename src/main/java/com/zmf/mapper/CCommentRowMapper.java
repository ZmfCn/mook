package com.zmf.mapper;

import com.zmf.pojo.CComment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CCommentRowMapper implements RowMapper<CComment> {

    public CComment mapRow(ResultSet resultSet, int i) throws SQLException {
        CComment cComment=new CComment();
        cComment.setCcommentId(resultSet.getString(1));
        cComment.setCommentedCommentId(resultSet.getString(2));
        cComment.setCcommentUserId(resultSet.getString(3));
        cComment.setCcommentTime(resultSet.getTimestamp(4));
        cComment.setIsChecked(resultSet.getBoolean(5));
        cComment.setIsBanned(resultSet.getBoolean(6));
        return cComment;
    }
}

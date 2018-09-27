package com.zmf.mapper;

import com.zmf.pojo.Keyword;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 22:19
 */
@Component
public class KeywordRowMapper implements RowMapper<Keyword> {

    @Override
    public Keyword mapRow(ResultSet rs, int rowNum) throws SQLException {
        Keyword keyword = new Keyword();
        keyword.setKeywordId(rs.getString(1));
        keyword.setContent(rs.getString(2));
        return keyword;
    }
}

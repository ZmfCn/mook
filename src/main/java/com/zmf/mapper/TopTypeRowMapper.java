package com.zmf.mapper;

import com.zmf.pojo.TopType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TopTypeRowMapper implements RowMapper<TopType> {
    public TopType mapRow(ResultSet resultSet, int i) throws SQLException {
        TopType topType = new TopType();
        topType.setTypeId(resultSet.getString(1));
        topType.setTypeName(resultSet.getString(2));
        return topType;
    }
}

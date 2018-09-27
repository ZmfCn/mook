package com.zmf.mapper;

import com.zmf.pojo.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getString(1));
        user.setUserName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setIsCommentExpired(resultSet.getBoolean(5));
        user.setIsConfirmed(resultSet.getBoolean(6));
        user.setRoleType(resultSet.getShort(7));
        return user;
    }
}

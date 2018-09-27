package com.zmf.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zmf.Utils.Util;
import com.zmf.mapper.UserRowMapper;
import com.zmf.pojo.User;
import com.zmf.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRowMapper userRowMapper;
    private Logger logger = Logger.getLogger(UserDao.class);

    @Test
    public void test() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ICourse?characterEncoding=utf8&useSSL=false");
            dataSource.setUser("zmf");
            dataSource.setPassword("123456sql");
            jdbcTemplate = new JdbcTemplate(dataSource);

//            for (int i = 0; i < 30; i++) {
//                String name = Util.convertToNineBits((int) (Math.random() * 999999999));
//                User user = new User();
//                user.setUserName(name);
//                user.setEmail(name + "@qq.com");
//                user.setUserId(new UserService().generateUid(user.getEmail(), System.currentTimeMillis()));
//                user.setPassword("123");
//                user.setRoleType((short) 0);
//                user.setIsConfirmed(true);
//                addUser(user);
//            }

            User user = new User();
            user.setUserName("normal");
            user.setEmail("3122548493@qq.com");
            user.setUserId(new UserService().generateUid(user.getEmail(), System.currentTimeMillis()));
            user.setPassword("123");
            user.setRoleType((short) 0);
            user.setIsConfirmed(true);
            addUser(user);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * add user
     *
     * @param user user
     */
    public void addUser(User user) {
        logger.info("user: add user... user name: " + user.getUserName());
        String sql = "INSERT INTO user\n" +
                "(`user_id`,\n" +
                "`user_name`,\n" +
                "`password`,\n" +
                "`email`,\n" +
                "`is_comment_expired`,\n" +
                "`is_confirmed`,\n" +
                "`role_type`)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getIsCommentExpired(),
                user.getIsConfirmed(),
                user.getRoleType());
    }


    /**
     * get users by permission code
     *
     * @param permissionCode the permission code
     * @return the users
     */
    public List<User> getUsersByPermissionCode(int permissionCode) {
        logger.info("user: get users by permission code... code: " + permissionCode);
        String sql = "SELECT * FROM user WHERE role_type = ?";
        try {
            return jdbcTemplate.query(sql, userRowMapper, permissionCode);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * get user by id
     *
     * @param userId the user id
     * @return the user
     */
    public User getUserById(String userId) {
        logger.info("user: get user by id... id: " + userId);
        String sql = "SELECT * FROM user WHERE user_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get user by name
     *
     * @param userName the user name
     * @return the user
     */
    public User getUserByName(String userName) {
        logger.info("user: get user by name... name: " + userName);
        String sql = "SELECT * FROM user WHERE user_name=?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, userName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get user by email
     *
     * @param email the email
     * @return the user
     */
    public User getUserByEmail(String email) {
        logger.info("user: get user by email... email: " + email);
        String sql = "SELECT * FROM user WHERE email=?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, email);
        } catch (Exception e) {
            return null;
        }
    }

    public void updateUserById(String userId, User user) {
        String sql = "UPDATE user\n" +
                "SET\n" +
                "'user_id'=?,\n" +
                "`user_name` =?,\n" +
                "`password` = ?,\n" +
                "`email` = ?,\n" +
                "`is_comment_expired` = ?,\n" +
                "`is_confirmed` = ?,\n" +
                "`role_type` = ?\n" +
                "WHERE `user_id` = ?;\n";
        jdbcTemplate.update(sql,
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getIsCommentExpired(),
                user.getIsConfirmed(),
                user.getRoleType(),
                userId);
    }

    //D
    public void deleteUser(String userId) {
        logger.info("user: ");
        String sql = "DELETE FROM user WHERE user_id=?";
        jdbcTemplate.update(sql, userId);
    }

    /**
     * set is confirmed
     *
     * @param isConfirmed is confirmed
     * @param userId      the user id
     */
    public void setUserConfirmed(boolean isConfirmed, String userId) {
        logger.info("user: set is confirmed... user id: " + userId + " is confirmed: " + isConfirmed);
        String sql = "UPDATE user SET is_confirmed = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, isConfirmed, userId);
    }

    /**
     * update user permission
     *
     * @param userId     the user id
     * @param permission the permission
     */
    public void updateUserPerMission(String userId, boolean permission) {
        logger.info("user: update user permission... user id: " + userId + " permission: " + permission);
        String sql = "UPDATE user SET is_comment_expired = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, permission, userId);
    }

    /**
     * update user password by name
     *
     * @param userName the user name
     * @param password the password
     */
    public void updateUserPasswordbyName(String userName, String password) {
        logger.info("user: update user password by name... name:" + userName);
        String sql = "UPDATE user SET password = ? WHERE user_name = ?";
        jdbcTemplate.update(sql, password, userName);
    }

    /**
     * update user password by the email
     *
     * @param email    the email
     * @param password the password
     */
    public void updateUserPasswordbyEmail(String email, String password) {
        logger.info("user: update user password by the email... email: " + email);
        String sql = "UPDATE user SET password = ? WHERE email = ?";
        jdbcTemplate.update(sql, password, email);
    }

    /**
     * delete all records unconfirmed
     */
    public void deleteAllUnconfirmed() {
        logger.info("user: delete all records unconfirmed...");
        String sql = "DELETE FROM user WHERE is_confirmed = FALSE ";
        jdbcTemplate.update(sql);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

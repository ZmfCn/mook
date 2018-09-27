package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.annotations.Tested;
import com.zmf.dao.UserDao;
import com.zmf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("loginService")
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Value("${server_ip}")
    private String server_ip;
    @Value("${server_port}")
    private String server_port;

    /**
     * judge whether an account is valid to log in, and complete the user
     * information including user id if the user is valid.
     *
     * @param user           the user instance
     * @param errorContainer the container of the error messages
     * @return true if it is valid
     */
    public boolean isValid(User user, List<String> errorContainer) {
        User result = userDao.getUserByName(user.getUserName());
        if (result == null) {
            errorContainer.add("用户名不存在");
            return false;
        } else {
            if (!result.getPassword().equals(user.getPassword())) {
                errorContainer.add("密码不正确");
                return false;
            }
            if (!result.getIsConfirmed()) {
                errorContainer.add("账号未激活,请点击邮箱中的链接进行激活");
                return false;
            }
        }

        // complete the information of the user
        user.setUserId(result.getUserId());
        user.setRoleType(result.getRoleType());
        return true;
    }

    /**
     * update the user password by the user name
     *
     * @param userName       the user name
     * @param prePassword    the previous password
     * @param newPassword    the new password
     * @param container      the message container
     * @param errorContainer the error message container
     * @return true if it is successful
     */
    public boolean updatePasswordByName(String userName, String prePassword, String newPassword,
                                        List<String> container, List<String> errorContainer) {
        if (Util.isEmpty(userName)) {
            errorContainer.add("用户名不能为空");
            return false;
        }
        User result = userDao.getUserByName(userName);
        if (result == null) {
            errorContainer.add("用户名不存在");
            return false;
        } else {
            if (!result.getPassword().equals(prePassword)) {
                errorContainer.add("原密码不正确");
                return false;
            }
            if (!result.getIsConfirmed()) {
                errorContainer.add("账号未激活,请点击邮箱中的链接进行激活");
                return false;
            }
            if (Util.isEmpty(newPassword)) {
                errorContainer.add("新密码不能为空");
                return false;
            }
            if (newPassword.length() < 1 || newPassword.length() > 15) {
                errorContainer.add("密码是1-15位之间的普通字符");
            }
            userDao.updateUserPasswordbyName(userName, newPassword);
            container.add("修改成功");
            return true;
        }
    }


    public boolean isEmailUsed(String email) {
        User result = userDao.getUserByEmail(email);
        return result != null && result.getIsConfirmed();
    }


    public boolean isPasswordValid(String password) {
        return password.length() >= 1 && password.length() <= 15;
    }

    /**
     * update the password by email
     *
     * @param email          the email
     * @param newPassword    the new password
     * @param container      the message container
     * @param errorContainer the error message container
     * @return true if it is successful
     */
    public boolean updatePasswordByEmail(String email, String newPassword,
                                         List<String> container, List<String> errorContainer) {
        User result = userDao.getUserByEmail(email);
        if (result == null) {
            errorContainer.add("邮箱未注册");
            return false;
        } else {
            if (!isPasswordValid(newPassword)) {
                errorContainer.add("密码是1-15位之间的普通字符");
            }
            userDao.updateUserPasswordbyEmail(email, newPassword);
            container.add("修改成功");
            return true;
        }
    }


    public String getUserNameByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        return user == null ? "" : user.getUserName();
    }

    /**
     * to judge the user id is in the database
     *
     * @param userId the user id
     * @return true if the user id is valid
     */
    public boolean isValidToComment(String userId) {
        User user = userDao.getUserById(userId);
        return user != null && !user.getIsCommentExpired();
    }

    /**
     * activate an account by the userId
     *
     * @param userId         the user id
     * @param container      the container of the messages
     * @param errorContainer the container of the error messages
     */
    public void confirmUser(String userId, List<String> container, List<String> errorContainer) {
        User result = userDao.getUserById(userId);
        if (result == null) {
            errorContainer.add("用户不存在或链接已失效");
        } else {
            if (result.getIsConfirmed()) {
                container.add("用户已激活");
            } else {
                userDao.setUserConfirmed(true, userId);
                container.add("用户已激活");
            }
        }
    }


    /**
     * add an account to the database
     *
     * @param user           the user instance
     * @param container      the container of the messages
     * @param errorContainer the container of the error messages
     * @return true if add a user
     */
    public boolean addUser(User user, List<String> container, List<String> errorContainer) {
        User result = userDao.getUserByName(user.getUserName());
        if (result != null) {
            errorContainer.add("用户名已存在");
            return false;
        } else {
            userDao.addUser(user);
            container.add("注册成功");
            return true;
        }
    }

    /**
     * generate the user id according to the user email and current millisecond.
     *
     * @param email         the email of user
     * @param currentMillis the current millisecond
     * @return the user id
     */
    @Tested
    public String generateUid(String email, long currentMillis) {
        String string = email.length() > 6 ? email.substring(0, 6) : email;
        return "u" + string + currentMillis;
    }


    public boolean updateUserCommentPermission(String userId, boolean permission) {
        userDao.updateUserPerMission(userId, permission);
        return true;
    }

    public String generateConfirmLink(String encrytedId) {
        return new StringBuilder().append("http://").append(server_ip).append(":").
                append(server_port).append("/user/confirm/").append(encrytedId).toString();
    }

    public String generateUserPasswordConfirmLink(String encryptedEmail, String encryptedPassword) {
        return new StringBuilder().append("http://").append(server_ip).append(":").
                append(server_port).append("/user/seek/").append(encryptedEmail).append("/").
                append(encryptedPassword).toString();
    }

    /**
     * delete all records unconfirmed
     */
    public void deleteAllUnconfirmed() {
        userDao.deleteAllUnconfirmed();
    }
}

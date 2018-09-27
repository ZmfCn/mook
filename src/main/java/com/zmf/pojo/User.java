package com.zmf.pojo;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

public class User {
    public static int NORMAL_USER = 0;
    public static int COURSE_MANEGER = 1;
    public static int SYSTEM_MANAGER = 2;
    public static int COMPANY_LEADER = 3;

    /**
     * This field corresponds to the database column user.user_id
     */
    private String userId;

    /**
     * This field corresponds to the database column user.user_name
     */
    @Size(min = 1, max = 30, message = "用户名不正确")
    private String userName;

    /**
     * This field corresponds to the database column user.password
     */
    @Size(min = 1, max = 15, message = "密码是1-15位之间的普通字符")
    private String password;

    /**
     * This field corresponds to the database column user.email
     */
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * This field corresponds to the database column user.is_comment_expired
     */
    private boolean isCommentExpired;

    /**
     * This field corresponds to the database column user.is_confirmed
     */
    private boolean isConfirmed;

    /**
     * This field corresponds to the database column user.role_type
     */
    private short roleType;

    /**
     * This method returns the value of the database column user.user_id
     *
     * @return the value of user.user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets the value of the database column user.user_id
     *
     * @param userId the value for user.user_id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method returns the value of the database column user.user_name
     *
     * @return the value of user.user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method sets the value of the database column user.user_name
     *
     * @param userName the value for user.user_name
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method returns the value of the database column user.is_comment_expired
     *
     * @return the value of user.is_comment_expired
     */
    public boolean getIsCommentExpired() {
        return isCommentExpired;
    }

    /**
     * This method sets the value of the database column user.is_comment_expired
     *
     * @param isCommentExpired the value for user.is_comment_expired
     */
    public void setIsCommentExpired(boolean isCommentExpired) {
        this.isCommentExpired = isCommentExpired;
    }

    /**
     * This method returns the value of the database column user.is_confirmed
     *
     * @return the value of user.is_confirmed
     */
    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    /**
     * This method sets the value of the database column user.is_confirmed
     *
     * @param isConfirmed the value for user.is_confirmed
     */
    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    /**
     * This method returns the value of the database column user.role_type
     *
     * @return the value of user.role_type
     */
    public short getRoleType() {
        return roleType;
    }

    /**
     * This method sets the value of the database column user.role_type
     *
     * @param roleType the value for user.role_type
     */
    public void setRoleType(short roleType) {
        this.roleType = roleType;
    }
}
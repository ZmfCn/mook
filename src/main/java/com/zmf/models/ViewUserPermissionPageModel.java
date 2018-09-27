package com.zmf.models;

import com.zmf.pojo.User;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 16:56
 */
public class ViewUserPermissionPageModel {
    private List<User> users;

    public ViewUserPermissionPageModel(List<User> users) {
        this.users = users;
    }

    public ViewUserPermissionPageModel() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

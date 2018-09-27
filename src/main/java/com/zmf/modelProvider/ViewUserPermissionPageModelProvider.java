package com.zmf.modelProvider;

import com.zmf.dao.UserDao;
import com.zmf.models.ViewUserPermissionPageModel;
import com.zmf.pojo.User;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 16:57
 */
@Component
public class ViewUserPermissionPageModelProvider {
    @Autowired
    UserDao userDao;
    @Autowired
    private EncryptionService encryptionService;

    public ViewUserPermissionPageModel getModel() {
        ViewUserPermissionPageModel model = new ViewUserPermissionPageModel();
        return setUsers(model);
    }

    private ViewUserPermissionPageModel setUsers(ViewUserPermissionPageModel model) {
        List<User> users = userDao.getUsersByPermissionCode(User.NORMAL_USER);
        int size = users.size();

        User user;
        for (int i = 0; i < size; i++) {
            user = users.get(i);
            user.setUserId(encryptionService.encrypt(user.getUserId()));
        }

        model.setUsers(users);
        return model;
    }
}

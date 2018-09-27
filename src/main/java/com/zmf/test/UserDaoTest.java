package com.zmf.test;

import com.zmf.dao.UserMapperDao;
import com.zmf.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HttpServletBean;

/**
 * @author zmf
 * @description
 * @timestamp 2018-01-26 18:56
 */
public class UserDaoTest extends BaseTest{
    @Autowired
    private UserMapperDao userMapperDao;

    @Test
    public void testQueryById() throws Exception{
        String id = "u1984651513065460806";
        User user = userMapperDao.queryById(id);

    }
}

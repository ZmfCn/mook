package com.zmf.dao;

import com.zmf.pojo.User;

/**
 * @author zmf
 * @description
 * @timestamp 2018-01-26 18:50
 */
public interface UserMapperDao {
    User queryById(String id) throws Exception;
}

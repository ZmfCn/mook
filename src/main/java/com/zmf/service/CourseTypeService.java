package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.pojo.CourseType;
import com.zmf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 14:58
 */
@Component
@Transactional
public class CourseTypeService {
    @Autowired
    private CourseTypeDao courseTypeDao;
    @Autowired
    private CourseDao courseDao;

    public boolean addACourseType(CourseType courseType, List<String> messages, List<String> errorMessages) {
        if (courseTypeDao.getCourseTypeByTypeName(courseType.getTypeName()) != null) {
            errorMessages.add("类别名已经存在");
            return false;
        } else {
            courseTypeDao.addCourseType(courseType);
            messages.add("添加成功");
            return true;
        }
    }

    public boolean deleteACourseType(String courseTypeId, List<String> messages, List<String> errorMessages) {
        if (courseTypeDao.getCourseTypeById(courseTypeId) == null) {
            errorMessages.add("类别不存在,无法删除");
            return false;
        }

        if (courseDao.getSubCourseNumberByCourseTypeId(courseTypeId) != 0) {
            errorMessages.add("类别不为空,无法删除");
            return false;
        }

        courseTypeDao.deleteCourseType(courseTypeId);
        messages.add("删除成功");
        return true;

    }

    /**
     * generate the course type id according to the user email and current millisecond.
     *
     * @param currentMillis the current millisecond
     * @return the id
     */
    public String generateCourseTypeId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("type");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }

    public boolean isUserPermittedToManageCourseType(int permissionCode) {
        return permissionCode == User.SYSTEM_MANAGER;
    }
}

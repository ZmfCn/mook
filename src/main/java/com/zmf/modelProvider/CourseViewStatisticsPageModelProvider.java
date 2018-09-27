package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.dao.TopTypeDao;
import com.zmf.models.CourseViewStatisticsPageModel;
import com.zmf.pojo.Course;
import com.zmf.pojo.CourseType;
import com.zmf.pojo.TopType;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-12 19:09
 */
@Component
public class CourseViewStatisticsPageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private TopTypeDao topTypeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseTypeDao courseTypeDao;

    public CourseViewStatisticsPageModel getModel() {
        CourseViewStatisticsPageModel model = new CourseViewStatisticsPageModel();
        return setContent(model);
    }

    private CourseViewStatisticsPageModel setContent(CourseViewStatisticsPageModel model) {
        List<Course> courses = courseDao.getAllCourses();
        List<CourseViewStatisticsPageModel.Course> contents = new ArrayList<>();
        int size = courses.size();
        Course course;
        CourseViewStatisticsPageModel.Course temp;
        for (int i = 0; i < size; i++) {
            course = courses.get(i);
            temp = model.new Course();
            temp.setContent(course);
            temp = setTopType(temp);
            temp.getContent().setCourseId(encryptionService.encrypt(temp.getContent().getCourseTypeId()));
            contents.add(temp);
        }
        model.setCourses(contents);
        return model;
    }

    private CourseViewStatisticsPageModel.Course setTopType(CourseViewStatisticsPageModel.Course course) {
        CourseType courseType = courseTypeDao.getCourseTypeById(course.getContent().getCourseTypeId());
        TopType topType = topTypeDao.getTopTypeById(courseType.getTopTypeId());
        topType.setTypeId(encryptionService.encrypt(topType.getTypeId()));
        course.setTopType(topType);
        return course;
    }
}

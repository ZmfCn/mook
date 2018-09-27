package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.dao.TopTypeDao;
import com.zmf.models.CourseTypeStatisticsPageModel;
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
 * @timestamp 2017-12-12 18:31
 */
@Component
public class CourseTypeStatisticsPageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private TopTypeDao topTypeDao;
    @Autowired
    private CourseTypeDao courseTypeDao;
    @Autowired
    private CourseDao courseDao;

    public CourseTypeStatisticsPageModel getModel() {
        CourseTypeStatisticsPageModel model = new CourseTypeStatisticsPageModel();
        return setList(model);
    }


    private CourseTypeStatisticsPageModel setList(CourseTypeStatisticsPageModel model) {
        List<TopType> topTypes = topTypeDao.getAllTopTypes();
        List<CourseTypeStatisticsPageModel.TopType> contents = new ArrayList<>();
        int size = topTypes.size();
        CourseTypeStatisticsPageModel.TopType topType;
        for (int i = 0; i < size; i++) {
            topType = model.new TopType();
            topType.setContent(topTypes.get(i));
            topType = setTopType(topType);
            topType.getContent().setTypeId(encryptionService.encrypt(topType.getContent().getTypeId()));
            contents.add(topType);
        }
        model.setTopTypes(contents);
        return model;
    }


    private CourseTypeStatisticsPageModel.TopType setTopType(CourseTypeStatisticsPageModel.TopType topType) {
        int count = 0;
        List<CourseType> courseTypes = courseTypeDao.getAllCourseTypesByParentTypeId(topType.getContent().getTypeId());
        int size = courseTypes.size();
        for (int i = 0; i < size; i++) {
            count += courseDao.getSubCourseNumberByCourseTypeId(courseTypes.get(i).getTypeId());
        }
        topType.setCourseNumber(count);
        return topType;
    }
}

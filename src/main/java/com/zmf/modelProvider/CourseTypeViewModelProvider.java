package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.dao.TopTypeDao;
import com.zmf.models.CourseTypeViewModel;
import com.zmf.pojo.CourseType;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-09 22:32
 */
@Component
public class CourseTypeViewModelProvider {
    @Autowired
    private CourseTypeDao courseTypeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TopTypeDao topTypeDao;
    @Autowired
    private EncryptionService encryptionService;

    public CourseTypeViewModel getModel() {
        CourseTypeViewModel courseTypeViewModel = new CourseTypeViewModel();
        courseTypeViewModel = setCourseTypes(courseTypeViewModel);
        return courseTypeViewModel;
    }

    private CourseTypeViewModel setCourseTypes(CourseTypeViewModel model) {
        List<CourseTypeViewModel.CourseType> courseTypes = new ArrayList<CourseTypeViewModel.CourseType>();
        List<CourseType> courseTypesInDb = courseTypeDao.getAllCourseTypes();

        int size = courseTypesInDb.size();
        CourseTypeViewModel.CourseType temp;
        CourseType ctemp;

        for (int i = 0; i < size; i++) {
            temp = model.new CourseType();
            ctemp = courseTypesInDb.get(i);
            ctemp.setTypeId(encryptionService.encrypt(ctemp.getTypeId()));
            ctemp.setTopTypeId(encryptionService.encrypt(ctemp.getTopTypeId()));
            temp.setCourseType(ctemp);
            temp = setSubCourseNumbers(temp);
            temp = setTopTypeName(temp);
            courseTypes.add(temp);
        }
        model.setCourseTypes(courseTypes);
        return model;
    }


    private CourseTypeViewModel.CourseType setSubCourseNumbers(CourseTypeViewModel.CourseType courseType) {
        courseType.setSubCourseNumber(courseDao.getSubCourseNumberByCourseTypeId(courseType.getCourseType().getTypeId()));
        return courseType;
    }

    private CourseTypeViewModel.CourseType setTopTypeName(CourseTypeViewModel.CourseType courseType) {
        courseType.setTopTypeName(topTypeDao.getTopTypeById(encryptionService.decrypt(courseType.getCourseType().getTopTypeId())).getTypeName());
        return courseType;
    }
}

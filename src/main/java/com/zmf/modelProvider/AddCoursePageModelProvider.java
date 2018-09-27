package com.zmf.modelProvider;

import com.zmf.dao.CourseTypeDao;
import com.zmf.models.AddCoursePageModel;
import com.zmf.pojo.CourseType;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 22:08
 */
@Component
public class AddCoursePageModelProvider {
    @Autowired
    private CourseTypeDao courseTypeDao;
    @Autowired
    private EncryptionService encryptionService;

    public AddCoursePageModel getModel() {
        AddCoursePageModel model = new AddCoursePageModel();
        return setCourseTypes(model);
    }

    private AddCoursePageModel setCourseTypes(AddCoursePageModel model) {
        List<CourseType> courseTypes = courseTypeDao.getAllCourseTypes();
        int size = courseTypes.size();
        CourseType temp;

        for (int i = 0; i < size; i++) {
            temp = courseTypes.get(i);
            temp.setTypeId(encryptionService.encrypt(temp.getTypeId()));
        }
        model.setCourseTypes(courseTypes);
        return model;
    }
}

package com.zmf.modelProvider;

import com.zmf.dao.TopTypeDao;
import com.zmf.models.AddCourseTypePageModel;
import com.zmf.pojo.TopType;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 00:03
 */
@Component
public class AddCourseTypePageModelProvider {
    @Autowired
    private TopTypeDao topTypeDao;
    @Autowired
    private EncryptionService encryptionService;

    public AddCourseTypePageModel getModel() {
        AddCourseTypePageModel addCourseTypePageModel = new AddCourseTypePageModel();
        addCourseTypePageModel = setTopTypes(addCourseTypePageModel);
        return addCourseTypePageModel;
    }

    private AddCourseTypePageModel setTopTypes(AddCourseTypePageModel addCourseTypePageModel) {
        List<TopType> topTypesInDb = topTypeDao.getAllTopTypes();
        int size = topTypesInDb.size();
        TopType temp;

        for (int i = 0; i < size; i++) {
            temp = topTypesInDb.get(i);
            temp.setTypeId(encryptionService.encrypt(temp.getTypeId()));
        }
        addCourseTypePageModel.setTopTypes(topTypesInDb);
        return addCourseTypePageModel;
    }
}

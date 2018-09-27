package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.dao.TopTypeDao;
import com.zmf.models.IndexPageModel;
import com.zmf.pojo.Course;
import com.zmf.pojo.CourseType;
import com.zmf.pojo.TopType;
import com.zmf.service.EncryptionService;
import com.zmf.service.FilePathMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description provide an instance of <tt>IndexPageModel</tt>
 * @timestamp 2017-12-07 21:33
 */
@SuppressWarnings("all")
@Component
public class IndexPageModelProvider {
    @Autowired
    private TopTypeDao topTypeDao;
    @Autowired
    private CourseTypeDao courseTypeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private FilePathMapperService filePathMapperService;

    /**
     * used to encrtpt all the id information
     */
    @Autowired
    private EncryptionService encryptionService;

    public IndexPageModel getModel() {
        IndexPageModel model = new IndexPageModel();
        setTopTypes(model);
        setRecommendCourses(model);
        setNewCourses(model);
        setImproveCourses(model);
        return model;
    }

    private IndexPageModel setTopTypes(IndexPageModel model) {
        IndexPageModel.TopType temp;
        TopType ttemp;

        List<IndexPageModel.TopType> topTypes = new ArrayList<IndexPageModel.TopType>();
        List<TopType> topTypesInDb = topTypeDao.getAllTopTypes();
        int size = topTypesInDb.size();

        for (int i = 0; i < size; i++) {
            temp = model.new TopType();
            ttemp = topTypesInDb.get(i);
            ttemp.setTypeId(encryptionService.encrypt(ttemp.getTypeId()));
            temp.setContent(ttemp);
            temp = setContentTopType(temp);
            topTypes.add(temp);
        }

        model.setTopTypes(topTypes);
        return model;
    }


    private IndexPageModel.TopType setContentTopType(IndexPageModel.TopType topType) {
        List<CourseType> courseTypes = courseTypeDao.getAllCourseTypesByParentTypeId(
                encryptionService.decrypt(topType.getContent().getTypeId()));
        int size = courseTypes.size();
        CourseType courseType;

        for (int i = 0; i < size; i++) {
            courseType = courseTypes.get(i);
            courseType.setTypeId(encryptionService.encrypt(courseType.getTypeId()));
        }
        topType.setCourseTypes(courseTypes);
        return topType;
    }

    private IndexPageModel setRecommendCourses(IndexPageModel model) {
        List<Course> courses = courseDao.getSeveralCoursesInAll(0, 4);
        int size = courses.size();
        for (int i = 0; i < size; i++) {
            courses.get(i).setCourseId(encryptionService.encrypt(courses.get(i).getCourseId()));
            courses.get(i).setImagePath(filePathMapperService.map(courses.get(i).getImagePath()));
        }
        model.setRecommendCourses(courses);
        return model;
    }

    private IndexPageModel setNewCourses(IndexPageModel model) {
        List<Course> courses = courseDao.getSeveralCoursesInAll(8, 4);
        int size = courses.size();
        for (int i = 0; i < size; i++) {
            courses.get(i).setCourseId(encryptionService.encrypt(courses.get(i).getCourseId()));
            courses.get(i).setImagePath(filePathMapperService.map(courses.get(i).getImagePath()));
        }
        model.setNewCourses(courses);
        return model;
    }

    private IndexPageModel setImproveCourses(IndexPageModel model) {
        List<Course> courses = courseDao.getSeveralCoursesInAll(4, 4);
        int size = courses.size();
        for (int i = 0; i < size; i++) {
            courses.get(i).setCourseId(encryptionService.encrypt(courses.get(i).getCourseId()));
            courses.get(i).setImagePath(filePathMapperService.map(courses.get(i).getImagePath()));
        }
        model.setImproveCourses(courses);
        return model;
    }
}

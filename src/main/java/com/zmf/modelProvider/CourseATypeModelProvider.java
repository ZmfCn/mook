package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.models.CourseATypeModel;
import com.zmf.pojo.Course;
import com.zmf.service.EncryptionService;
import com.zmf.service.FilePathMapperService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-08 21:52
 */
@Component
public class CourseATypeModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseTypeDao courseTypeDao;
    @Autowired
    private FilePathMapperService filePathMapperService;

    /**
     * get the page model by the course type id and page number
     *
     * @param typeId     the type id that is not encrypted
     * @param pageNumber the page number
     * @return the page model
     */
    public CourseATypeModel getPageModel(String typeId, int pageNumber) {
        CourseATypeModel courseATypeModel = new CourseATypeModel();
        courseATypeModel = setCourseTypeName(courseATypeModel, typeId);
        courseATypeModel = setCoursesInCurrentPage(courseATypeModel, typeId, pageNumber);
        courseATypeModel = setPageNumber(courseATypeModel, typeId);
        courseATypeModel.setCourseTypeId(encryptionService.encrypt(typeId));
        return courseATypeModel;
    }


    private CourseATypeModel setCourseTypeName(CourseATypeModel model, String typeId) {
        model.setPageTypeName(courseTypeDao.getCourseTypeById(typeId).getTypeName());
        return model;
    }

    /**
     * set the page content of the certain page number
     *
     * @param courseATypeModel the model
     * @param typeId           the type id that is not encrypted
     * @param pageNumber       the page number
     * @return page model
     */
    private CourseATypeModel setCoursesInCurrentPage(CourseATypeModel courseATypeModel, String typeId, int pageNumber) {
        List<Course> courses = courseDao.getSeveralCoursesByCourseTypeId(typeId,
                getStartPosition(pageNumber), CourseATypeModel.PAGE_COURSE_NUMBER);
        int size = courses.size();
        for (int i = 0; i < size; i++) {
            courses.get(i).setCourseId(encryptionService.encrypt(courses.get(i).getCourseId()));
            courses.get(i).setImagePath(filePathMapperService.map(courses.get(i).getImagePath()));
        }
        courseATypeModel.setCourses(courses);
        return courseATypeModel;
    }

    /**
     * set the number of pages
     *
     * @param courseATypeModel model
     * @param typeId           the type id that is not encrypted
     * @return model
     */
    private CourseATypeModel setPageNumber(CourseATypeModel courseATypeModel, String typeId) {
        int recordNumber = courseDao.getSubCourseNumberByCourseTypeId(typeId);
        courseATypeModel.setPages((int) Math.ceil(recordNumber * 1.0 / CourseATypeModel.PAGE_COURSE_NUMBER));
        return courseATypeModel;
    }

    private int getStartPosition(int pageNumber) {
        return CourseATypeModel.PAGE_COURSE_NUMBER * pageNumber;
    }

}

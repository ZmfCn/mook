package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseTypeDao;
import com.zmf.models.CourseATypeModel;
import com.zmf.models.CourseAllPageModel;
import com.zmf.pojo.Course;
import com.zmf.service.EncryptionService;
import com.zmf.service.FilePathMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-08 21:52
 */
@Component
public class CourseAllPageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private FilePathMapperService filePathMapperService;

    /**
     * get the page model by page number
     *
     * @param pageNumber the page number
     * @return the page model
     */
    public CourseAllPageModel getPageModel(int pageNumber) {
        CourseAllPageModel courseAllPageModel = new CourseAllPageModel();
        courseAllPageModel = setCoursesInCurrentPage(courseAllPageModel, pageNumber);
        courseAllPageModel = setPageNumber(courseAllPageModel);
        courseAllPageModel.setCurrentPage(pageNumber);
        return courseAllPageModel;
    }


    /**
     * set the page content of the certain page number
     *
     * @param courseAllPageModel the model
     * @param pageNumber         the page number
     * @return page model
     */
    private CourseAllPageModel setCoursesInCurrentPage(CourseAllPageModel courseAllPageModel, int pageNumber) {
        List<Course> courses = courseDao.getSeveralCoursesInAll(getStartPosition(pageNumber),
                CourseAllPageModel.PAGE_COURSE_NUMBER);
        int size = courses.size();
        for (int i = 0; i < size; i++) {
            courses.get(i).setCourseId(encryptionService.encrypt(courses.get(i).getCourseId()));
            courses.get(i).setImagePath(filePathMapperService.map(courses.get(i).getImagePath()));
        }
        courseAllPageModel.setCourses(courses);
        return courseAllPageModel;
    }

    /**
     * set the number of pages
     *
     * @param courseAllPageModel model
     * @return model
     */
    private CourseAllPageModel setPageNumber(CourseAllPageModel courseAllPageModel) {
        int recordNumber = courseDao.getCourseNumber();
        courseAllPageModel.setPages((int) Math.ceil(recordNumber * 1.0 / CourseAllPageModel.PAGE_COURSE_NUMBER));
        return courseAllPageModel;
    }

    private int getStartPosition(int pageNumber) {
        return CourseAllPageModel.PAGE_COURSE_NUMBER * pageNumber;
    }

}

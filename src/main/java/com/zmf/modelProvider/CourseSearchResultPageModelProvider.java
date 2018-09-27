package com.zmf.modelProvider;

import com.zmf.dao.CourseDao;
import com.zmf.models.CourseAllPageModel;
import com.zmf.models.CourseSearchResultPageModel;
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
public class CourseSearchResultPageModelProvider {
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
    public CourseSearchResultPageModel getPageModel(String string, int pageNumber) {
        CourseSearchResultPageModel courseSearchResultPageModel = new CourseSearchResultPageModel();
        courseSearchResultPageModel = setCoursesInCurrentPage(courseSearchResultPageModel, string, pageNumber);
        courseSearchResultPageModel = setPageNumber(courseSearchResultPageModel, string);
        courseSearchResultPageModel.setCurrentPage(pageNumber);
        courseSearchResultPageModel.setString(string);
        return courseSearchResultPageModel;
    }


    /**
     * set the page content of the certain page number
     *
     * @param courseSearchResultPageModel the model
     * @param string                      the string to match
     * @param pageNumber                  the page number
     * @return page model
     */
    private CourseSearchResultPageModel setCoursesInCurrentPage(CourseSearchResultPageModel courseSearchResultPageModel, String string, int pageNumber) {
        List<Course> courses = courseDao.getSeveralCourseLike(string, getStartPosition(pageNumber),
                CourseSearchResultPageModel.PAGE_COURSE_NUMBER);
        System.out.println("----------->size: " + courses.size());
        int size = courses.size();
        for (int i = 0; i < size; i++) {
            courses.get(i).setCourseId(encryptionService.encrypt(courses.get(i).getCourseId()));
            courses.get(i).setImagePath(filePathMapperService.map(courses.get(i).getImagePath()));
        }
        courseSearchResultPageModel.setCourses(courses);
        return courseSearchResultPageModel;
    }

    /**
     * set the number of pages
     *
     * @param courseSearchResultPageModel model
     * @param string                      the string to match
     * @return model
     */
    private CourseSearchResultPageModel setPageNumber(CourseSearchResultPageModel courseSearchResultPageModel, String string) {
        int recordNumber = courseDao.getTheNumberOfCourseLike(string);
        System.out.println("------------------>record number: " + recordNumber);
        courseSearchResultPageModel.setPages((int) Math.ceil(recordNumber * 1.0 / CourseSearchResultPageModel.PAGE_COURSE_NUMBER));
        return courseSearchResultPageModel;
    }

    private int getStartPosition(int pageNumber) {
        return CourseSearchResultPageModel.PAGE_COURSE_NUMBER * pageNumber;
    }
}

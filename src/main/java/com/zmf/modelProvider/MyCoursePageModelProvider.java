package com.zmf.modelProvider;

import com.zmf.Utils.Util;
import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseLearningRecordDao;
import com.zmf.models.CourseATypeModel;
import com.zmf.models.MyCoursePageModel;
import com.zmf.pojo.Course;
import com.zmf.pojo.CourseLearningRecord;
import com.zmf.service.EncryptionService;
import com.zmf.service.FilePathMapperService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description to provide the data model of my course page
 * @timestamp 2017-12-08 09:37
 */
@Component
@SuppressWarnings("all")
public class MyCoursePageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CourseLearningRecordDao courseLearningRecordDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private FilePathMapperService filePathMapperService;
    Logger logger = Logger.getLogger(MyCoursePageModelProvider.class);

    /**
     * @param userId     the user id
     * @param pageNumber the page number
     * @return the model
     */
    public MyCoursePageModel getModel(String userId, int pageNumber) {
        MyCoursePageModel myCoursePageModel = new MyCoursePageModel();
        myCoursePageModel.setCurrentPage(pageNumber);
        myCoursePageModel = setCoursesInCurrentPage(myCoursePageModel, pageNumber, userId);
        myCoursePageModel = setPageNumbers(myCoursePageModel, userId);
        return myCoursePageModel;
    }

    /**
     * set the page content of the certain page number
     *
     * @param myCoursePageModel the model
     * @param pageNumber        the page number
     * @param userId            the user id
     * @return page model
     */
    private MyCoursePageModel setCoursesInCurrentPage(MyCoursePageModel myCoursePageModel, int pageNumber, String userId) {
        List<Course> courses = courseDao.getSeveralCoursesByUserId(userId,
                getStartPosition(pageNumber), MyCoursePageModel.PAGE_COURSE_NUMBER);
        List<MyCoursePageModel.Course> myCourses = new ArrayList<>();
        int size = courses.size();
        logger.info("------------------------>size: " + size);
        Course course;
        MyCoursePageModel.Course myCourse;
        for (int i = 0; i < size; i++) {
            myCourse = myCoursePageModel.new Course();
            course = courses.get(i);
            myCourse.setDate(getTime(course.getCourseId(), userId));
            course.setCourseId(encryptionService.encrypt(course.getCourseId()));
            course.setCourseTypeId(encryptionService.encrypt(course.getCourseTypeId()));
            course.setImagePath(filePathMapperService.map(course.getImagePath()));
            myCourse.setCourse(course);
            myCourses.add(myCourse);
        }
        myCoursePageModel.setCourses(myCourses);
        return myCoursePageModel;
    }


    @Test
    public void test() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    }

    private String getTime(String courseId, String userId) {
        CourseLearningRecord record;
        if ((record = courseLearningRecordDao.getRecordByUserIdAndCourseId(courseId, userId)) != null) {
            return Util.getDateString(record.getStartTime());
        } else {
            return "";
        }
    }

    /**
     * set the number of pages
     *
     * @param myCoursePageModel model
     * @param userId            the user id
     * @return model
     */
    private MyCoursePageModel setPageNumbers(MyCoursePageModel myCoursePageModel, String userId) {
        int recordNumber = courseLearningRecordDao.getRecordNumberByUserId(userId);
        myCoursePageModel.setPages((int) Math.ceil(recordNumber * 1.0 / MyCoursePageModel.PAGE_COURSE_NUMBER));
        return myCoursePageModel;
    }

    private int getStartPosition(int pageNumber) {
        return MyCoursePageModel.PAGE_COURSE_NUMBER * pageNumber;
    }
}

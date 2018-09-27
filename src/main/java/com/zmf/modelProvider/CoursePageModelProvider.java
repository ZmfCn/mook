package com.zmf.modelProvider;

import com.zmf.dao.ChapterDao;
import com.zmf.dao.CourseDao;
import com.zmf.dao.CourseLearningRecordDao;
import com.zmf.models.CoursePageModel;
import com.zmf.pojo.Chapter;
import com.zmf.pojo.Course;
import com.zmf.service.ChapterService;
import com.zmf.service.EncryptionService;
import com.zmf.service.FilePathMapperService;
import com.zmf.service.MultiMediaDurationService;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author zmf
 * @description to provide a course page model
 * @timestamp 2017-12-09 11:02
 */
@Component
public class CoursePageModelProvider {
    @Autowired
    private CourseLearningRecordDao courseLearningRecordDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private FilePathMapperService filePathMapperService;
    @Autowired
    private MultiMediaDurationService multiMediaDurationService;
    @Autowired
    private ChapterService chapterService;

    /**
     * get a course page model by the course id
     *
     * @param courseId the course id
     * @param userId   the id of user
     * @return the page model
     */
    public CoursePageModel getModel(String courseId, String userId) {
        CoursePageModel coursePageModel = new CoursePageModel();
        coursePageModel = setCourse(coursePageModel, courseId);
        coursePageModel = setChapters(coursePageModel, courseId);
        coursePageModel = setIsAdded(coursePageModel, courseId, userId);
        return coursePageModel;
    }

    private CoursePageModel setIsAdded(CoursePageModel model, String courseId, String userId) {
        model.setUserAddedToLearn(courseLearningRecordDao.getRecordByUserIdAndCourseId(courseId, userId) != null);
        return model;
    }

    private CoursePageModel setCourse(CoursePageModel model, String courseId) {
        Course course = courseDao.getCourseById(courseId);
        course.setCourseId(encryptionService.encrypt(course.getCourseId()));
        course.setCourseTypeId(encryptionService.encrypt(course.getCourseTypeId()));
        course.setImagePath(filePathMapperService.map(course.getImagePath()));
        model.setCourse(course);
        return model;
    }

    private CoursePageModel setChapters(CoursePageModel model, String courseId) {
        List<Chapter> chapters = chapterDao.getChaptersByOwnerCourseId(courseId);
        int size = chapters.size();
        Chapter temp;
        for (int i = 0; i < size; i++) {
            temp = chapters.get(i);
            if (!temp.getIsPdf() && (temp.getDuration() == null || temp.getDuration().length() == 0)) {
                String filePath = temp.getFilepath();
                String chapterId = temp.getChapterId();
                new Thread(() -> {
                    try {
                        String duration = multiMediaDurationService.getDuration(new File(filePath));
                        chapterService.setDuration(chapterId, duration);
                    } catch (EncoderException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            temp.setChapterId(encryptionService.encrypt(temp.getChapterId()));
            temp.setOwnerCourseId(encryptionService.encrypt(temp.getOwnerCourseId()));
        }
        model.setChapters(chapters);
        return model;
    }
}

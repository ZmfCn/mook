package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.dao.CourseLearningRecordDao;
import com.zmf.pojo.CourseLearningRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-13 18:39
 */
@Component
public class CourseLearningRecordService {
    @Autowired
    private CourseLearningRecordDao courseLearningRecordDao;
    Logger logger = Logger.getLogger(CourseLearningRecord.class);

    public void addToLearn(String courseId, String userId) {
        logger.info("----------------> add course learning record: " + courseId + " " + userId);
        if (courseLearningRecordDao.getRecordByUserIdAndCourseId(courseId, userId) == null) {
            CourseLearningRecord courseLearningRecord = new CourseLearningRecord();
            courseLearningRecord.setRecordId(generateRecordId(System.currentTimeMillis()));
            courseLearningRecord.setStartTime(new Timestamp(System.currentTimeMillis()));
            courseLearningRecord.setCourseId(courseId);
            courseLearningRecord.setUserId(userId);
            courseLearningRecordDao.addCourseLearningRecord(courseLearningRecord);
            logger.info("----------------> add course learning record: successful");
        }
    }

    public void deleteRecord(String recordId) {
        CourseLearningRecord record;
        if ((record = courseLearningRecordDao.getCourseLearningRecordById(recordId)) != null) {
            courseLearningRecordDao.deleteCourseLearningRecord(recordId);
        }
    }


    public void deleteRecordsByCourseId(String courseId) {
        courseLearningRecordDao.deleteRecordsByCourseId(courseId);
    }


    public String generateRecordId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("crd");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }
}

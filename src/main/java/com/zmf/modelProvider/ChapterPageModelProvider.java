package com.zmf.modelProvider;

import com.zmf.dao.ChapterDao;
import com.zmf.dao.CourseDao;
import com.zmf.models.ChapterPageModel;
import com.zmf.pojo.Chapter;
import com.zmf.pojo.Course;
import com.zmf.service.EncryptionService;
import com.zmf.service.FilePathMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-14 16:11
 */
@Component
public class ChapterPageModelProvider {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private FilePathMapperService filePathMapperService;

    public ChapterPageModel getModel(String chapterId) {
        ChapterPageModel chapterPageModel = new ChapterPageModel();
        chapterPageModel = setChapter(chapterPageModel, chapterId);
        chapterPageModel = setNextChapterId(chapterPageModel);
        chapterPageModel = setCourseName(chapterPageModel);
        return chapterPageModel;
    }

    private ChapterPageModel setChapter(ChapterPageModel model, String chapterId) {
        Chapter chapter = chapterDao.getChapterById(chapterId);
        chapter.setOwnerCourseId(encryptionService.encrypt(chapter.getOwnerCourseId()));
        chapter.setChapterId(encryptionService.encrypt(chapter.getChapterId()));
        chapter.setFilepath(filePathMapperService.map(chapter.getFilepath()));
        model.setChapter(chapter);
        return model;
    }

    private ChapterPageModel setNextChapterId(ChapterPageModel model) {
        Chapter chapter = chapterDao.getChapterByOrderAndCourseId(model.getChapter().getChapterOrder() + 1,
                encryptionService.decrypt(model.getChapter().getOwnerCourseId()));
        if (chapter != null) {
            model.setEncryptedNextChapterId(encryptionService.encrypt(chapter.getChapterId()));
        } else {
            model.setEncryptedNextChapterId("none");
        }
        return model;
    }

    private ChapterPageModel setCourseName(ChapterPageModel model) {
        Course course = courseDao.getCourseById(encryptionService.decrypt(model.getChapter().getOwnerCourseId()));
        model.setCourseName(course.getCourseName());
        return model;
    }
}

package com.zmf.modelProvider;

import com.zmf.dao.ChapterDao;
import com.zmf.models.CatalogModel;
import com.zmf.pojo.Chapter;
import com.zmf.service.ChapterService;
import com.zmf.service.EncryptionService;
import com.zmf.service.MultiMediaDurationService;
import it.sauronsoftware.jave.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-15 18:41
 */
@Component
public class CatalogModelProvider {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private MultiMediaDurationService multiMediaDurationService;
    @Autowired
    private ChapterService chapterService;

    public CatalogModel getModel(String courseId) {
        CatalogModel catalogModel = new CatalogModel();
        catalogModel = setChapters(catalogModel, courseId);
        return catalogModel;
    }

    private CatalogModel setChapters(CatalogModel model, String courseId) {
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

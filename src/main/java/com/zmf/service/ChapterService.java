package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.dao.ChapterCommentDao;
import com.zmf.dao.ChapterDao;
import com.zmf.dao.CourseDao;
import com.zmf.pojo.Chapter;
import com.zmf.pojo.ChapterComment;
import com.zmf.pojo.Course;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-12 23:50
 */
@Component
@SuppressWarnings("all")
@Transactional
public class ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private ChapterCommentDao chapterCommentDao;
    @Autowired
    private ChapterCommentService chapterCommentService;
    @Autowired
    private CourseDao courseDao;

    private Logger logger = Logger.getLogger(ChapterService.class);


    /**
     * to judge the chapter id is in the database
     *
     * @param chapterId the chapter id
     * @return true if is valid
     */
    public boolean isValid(String chapterId) {
        return chapterDao.getChapterById(chapterId) != null;
    }


    /**
     * add a course to the database and to the disk
     *
     * @param container         the message container
     * @param errorContainer    the error message container
     * @param encryptedCourseId the encryptedCourseId
     * @param order             the chapter order
     * @param chapterName       the chapter number
     * @param file              the file that is either video or pdf
     * @return true if successful
     */
    public boolean addChapter(List<String> container, List<String> errorContainer, String encryptedCourseId,
                              String order, String chapterName, MultipartFile file) {
        String courseId = encryptionService.decrypt(encryptedCourseId);
        int chapterOrder = 0;
        try {
            chapterOrder = Integer.parseInt(order);
        } catch (Exception e) {
            errorContainer.add("章节顺序只能是正整数");
            return false;
        }
        if (!isChapterOrderValid(chapterOrder, courseId)) {
            errorContainer.add("章节顺序有误");
            return false;
        }

        if (Util.isEmpty(chapterName)) {
            errorContainer.add("章节名不能为空");
            return false;
        }

        if (!file.isEmpty()) {
            StringBuilder fileName = new StringBuilder();
            fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
            String contentType = file.getContentType();
            boolean isPdf = false;
            logger.info("-------------->file type: " + contentType);
            if (contentType.trim().equals("application/pdf")) {
                isPdf = true;
            } else if (contentType.startsWith("video/")) {
                isPdf = false;
            } else {
                errorContainer.add("上传文件不合法");
                return false;
            }
            //获得文件后缀名称
            fileName.append(".").append(contentType.substring(contentType.indexOf("/") + 1));
            logger.info("---------------------->file name: " + fileName);
            Chapter chapter = generateChapter(courseId, chapterName, fileName.toString(), chapterOrder, isPdf);

            new Thread(() -> {
                try {
                    File dest = new File(chapter.getFilepath());
                    Util.createFile(dest);
                    file.transferTo(dest);
                    Util.changeFilePermission(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            addChapter(chapter);
            container.add("添加成功");
            return true;
        } else {
            errorContainer.add("上传文件为空");
            return false;
        }
    }

    /**
     * alter the chapter information
     *
     * @param container      the message container
     * @param errorContainer the error message container
     * @param chapterId      the chapter id
     * @param order          the new order of the chapter
     * @param chapterName    the new name of the chapter
     * @param file           the new file of the chapter
     * @return true if it is successful
     */
    public boolean alterChapter(List<String> container, List<String> errorContainer, String chapterId,
                                String order, String chapterName, MultipartFile file) {
        Chapter chapter = chapterDao.getChapterById(chapterId);
        if (chapter == null) {
            return false;
        }

        if (!Util.isEmpty(order)) {
            String courseId = chapter.getOwnerCourseId();
            int chapterOrder = 0;
            try {
                chapterOrder = Integer.parseInt(order);
            } catch (Exception e) {
                errorContainer.add("章节顺序只能是正整数");
                return false;
            }
            if (!isChapterOrderValid(chapterOrder, courseId)) {
                errorContainer.add("章节顺序有误");
                return false;
            }

            updateChapterOrder(chapter.getChapterId(), chapterOrder);
            chapter.setChapterOrder(chapterOrder);
        }

        if (!Util.isEmpty(chapterName)) {
            chapter.setChapterTitle(chapterName);
        }

        if (!file.isEmpty()) {
            StringBuilder fileName = new StringBuilder();
            fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
            String contentType = file.getContentType();
            boolean isPdf = false;
            logger.info("-------------->file type: " + contentType);
            if (contentType.trim().equals("application/pdf")) {
                isPdf = true;
            } else if (contentType.trim().equals("video/mp4")) {
                isPdf = false;
            } else {
                errorContainer.add("上传文件不合法");
                return false;
            }
            //获得文件后缀名称
            fileName.append(".").append(contentType.substring(contentType.indexOf("/") + 1));
            logger.info("---------------------->file name: " + fileName);
            String preFilePath = chapter.getFilepath();
            chapter.setFilepath(generateFilePath(chapter.getOwnerCourseId(), fileName.toString()));
            Util.writeFile(chapter.getFilepath(), file);
            Util.deleteFile(new File(preFilePath));
            chapter.setIsPdf(isPdf);
            chapter.setDuration("");
        }
        chapterDao.updateChapterById(chapter);
        container.add("修改成功");
        return true;
    }


    /**
     * update the chapter order and the involved chapters id
     *
     * @param chapterId the chapter id
     * @param newOrder  the new order that is valid
     */
    public void updateChapterOrder(String chapterId, int newOrder) {
        Chapter chapter = chapterDao.getChapterById(chapterId);
        if (chapter != null) {
            int preOrder = chapter.getChapterOrder();
            if (preOrder == newOrder) {
                return;
            } else if (preOrder > newOrder) {
                List<Chapter> chapters = chapterDao.getSeveralChaptersAfterCertainOrder(chapter.getOwnerCourseId(),
                        newOrder, preOrder - newOrder);
                int size = chapters.size();
                Chapter temp;
                for (int i = 0; i < size; i++) {
                    temp = chapters.get(i);
                    temp.setChapterOrder(temp.getChapterOrder() + 1);
                    chapterDao.updateChapterOrder(temp);
                }
                chapter.setChapterOrder(newOrder);
                chapterDao.updateChapterOrder(chapter);
            } else {
                List<Chapter> chapters = chapterDao.getSeveralChaptersAfterCertainOrder(chapter.getOwnerCourseId(),
                        preOrder + 1, newOrder - preOrder);
                int size = chapters.size();
                Chapter temp;
                for (int i = 0; i < size; i++) {
                    temp = chapters.get(i);
                    temp.setChapterOrder(temp.getChapterOrder() - 1);
                    chapterDao.updateChapterOrder(temp);
                }
                chapter.setChapterOrder(newOrder);
                chapterDao.updateChapterOrder(chapter);
            }
        }
    }

    public Chapter getChapterById(String chapterId) {
        return chapterDao.getChapterById(chapterId);
    }

    private void addChapter(Chapter chapter) {
        List<Chapter> chapters = chapterDao.getChaptersAfterCertainOrder(chapter.getOwnerCourseId(),
                chapter.getChapterOrder());
        int size = chapters.size();
        Chapter temp;
        for (int i = 0; i < size; i++) {
            temp = chapters.get(i);
            temp.setChapterOrder(temp.getChapterOrder() + 1);
            chapterDao.updateChapterOrder(temp);
        }
        chapterDao.addChapter(chapter);
    }

//    public boolean deleteChapter(Chapter chapter, List<String> container, List<String> errorContainer) {
//        if (chapterDao.getChapterById(chapter.getChapterId()) == null) {
//            errorContainer.add("章节不存在,无需删除");
//            return false;
//        } else {
//            // delete the record
//            deleteChapter(chapter);
//            container.add("删除成功");
//            return true;
//        }
//    }


    /**
     * delete a chapter including its file on disk and its conments.
     *
     * @param chapter the chapter
     * @return true if successful
     */
    public boolean deleteChapter(Chapter chapter) {
        if (chapterDao.getChapterById(chapter.getChapterId()) == null) {
            return false;
        } else {


            // delete the record
            deleteChapter(chapter.getChapterId());
            return true;
        }
    }

    /**
     * set the duration of the chapter video
     *
     * @param chapterId
     * @param duration
     */
    public void setDuration(String chapterId, String duration) {
        Chapter chapter;
        if ((chapter = chapterDao.getChapterById(chapterId)) != null && !chapter.getIsPdf()) {
            chapterDao.updateChapterDuration(chapterId, duration);
        }
    }

    /**
     * delete a chapter including its file on disk and its conments.
     *
     * @param chapterId the chapter id
     * @return true if successful
     */
    public boolean deleteChapter(String chapterId) {
        Chapter chapter = null;
        if ((chapter = chapterDao.getChapterById(chapterId)) == null) {
            return false;
        } else {
            //delete the comments
            List<ChapterComment> chapterComments = chapterCommentDao.getChapterCommentsByChapterId(chapterId);
            int size = chapterComments.size();

            for (int i = 0; i < size; i++) {
                chapterCommentService.deleteChapterComment(chapterComments.get(i));
            }
            // delete the file
            Util.deleteFile(new File(chapter.getFilepath()));
            // delete the record
            chapterDao.deleteChapter(chapter.getChapterId());
            // update the order after this chapter
            List<Chapter> chapters = chapterDao.getChaptersAfterCertainOrder(chapter.getOwnerCourseId(), chapter.getChapterOrder());
            size = chapters.size();
            Chapter temp;
            for (int i = 0; i < size; i++) {
                temp = chapters.get(i);
                temp.setChapterOrder(temp.getChapterOrder() - 1);
                chapterDao.updateChapterOrder(temp);
            }
            return true;
        }
    }

    private boolean isChapterOrderValid(int order, String courseId) {
        int number = chapterDao.getChapterNumberByOwnerCourseId(courseId);
        if (order < 1) {
            return false;
        }
        if (order > number + 1) {
            return false;
        }
        return true;
    }

    public Chapter generateChapter(String courseId, String chapterName, String fileName, int chapterOrder, boolean isPdf) {
        Chapter chapter = new Chapter();
        chapter.setOwnerCourseId(courseId);
        chapter.setChapterId(generateChapterId(System.currentTimeMillis()));
        chapter.setChapterOrder(chapterOrder);
        chapter.setDuration("");
        chapter.setChapterTitle(chapterName);
        chapter.setIsPdf(isPdf);
        chapter.setFilepath(generateFilePath(courseId, fileName));
        return chapter;
    }

    public String generateFilePath(String courseId, String fileName) {
        Course course = courseDao.getCourseById(courseId);
        return course.getCoursePath() + "/" + fileName;
    }

    public String generateChapterId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("chapter");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }
}

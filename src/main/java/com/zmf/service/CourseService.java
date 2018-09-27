package com.zmf.service;

import com.zmf.Utils.Util;
import com.zmf.dao.ChapterDao;
import com.zmf.dao.CourseDao;
import com.zmf.pojo.Chapter;
import com.zmf.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 22:40
 */
@Component
@Transactional
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Value("${file_root_path}")
    private String rootPath;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CourseLearningRecordService courseLearningRecordService;


    /**
     * add count of the courseId
     *
     * @param courseId course id
     */
    public void addCount(String courseId) {
        courseDao.addCount(courseId);
    }

    private boolean addCourse(Course course, List<String> container, List<String> errorContainer) {
        if (courseDao.getCourseByName(course.getCourseName()) != null) {
            errorContainer.add("课程名已存在");
            return false;
        } else {
            courseDao.addCourse(course);
            container.add("添加成功");
            return true;
        }
    }


    /**
     * @param container      the message container
     * @param errorContainer the error message container
     * @param file           the new file
     * @param preName        the previous name
     * @param newName        the new name
     * @param teacherName    the new teacher name
     */
    public void alterCourse(List<String> container, List<String> errorContainer, MultipartFile file,
                            String preName, String newName, String teacherName) {
        if (Util.isEmpty(preName)) {
            errorContainer.add("原课程名不能为空");
        } else {
            Course course = courseDao.getCourseByName(preName);
            if (course == null) {
                errorContainer.add("要修改的课程不存在");
                return;
            }
            if (!Util.isEmpty(newName)) {
                if (courseDao.getCourseByName(newName) != null) {
                    errorContainer.add("课程名无法重复");
                    return;
                } else {
                    course.setCourseName(newName);
                }
            }
            if (!Util.isEmpty(teacherName)) {
                course.setTeacherName(teacherName);
            }
            if (!file.isEmpty()) {
                //获得文件类型（可以判断如果不是图片，禁止上传）
                StringBuilder imageName = new StringBuilder();
                imageName.append(UUID.randomUUID().toString().replaceAll("-", ""));
                String contentType = file.getContentType();
                if (!contentType.startsWith("image")) {
                    errorContainer.add("上传的不是图片");
                } else {
                    //获得文件后缀名称
                    imageName.append(".").append(contentType.substring(contentType.indexOf("/") + 1));
                    String path = generateImageFilePath(course.getCoursePath(), imageName.toString());
                    Util.writeFile(path, file);
                    Util.deleteFile(new File(course.getImagePath()));
                    course.setImagePath(path);
                }
            }
            courseDao.updateCourseById(course);
            container.add("修改成功");
        }
    }

    /**
     * add a course to the database and to the disk
     *
     * @param container      the message container
     * @param errorContainer the error message container
     * @param file           the file content from client
     * @param parentTypeId   the course type id
     * @param courseName     the course name
     * @param teacherName    the teacher name of the course
     */
    public void addCourse(List<String> container, List<String> errorContainer, MultipartFile file,
                          String parentTypeId, String courseName, String teacherName) {
        if (!file.isEmpty()) {
            if (courseDao.getCourseByName(courseName) != null) {
                errorContainer.add("课程名无法重复");
                return;
            }

            //获得文件类型（可以判断如果不是图片，禁止上传）
            StringBuilder imageName = new StringBuilder();
            imageName.append(UUID.randomUUID().toString().replaceAll("-", ""));
            String contentType = file.getContentType();
            if (!contentType.startsWith("image")) {
                errorContainer.add("上传的不是图片");
            } else {
                //获得文件后缀名称
                imageName.append(".").append(contentType.substring(contentType.indexOf("/") + 1));

                Course course = generateCourse(courseName, encryptionService.decrypt(parentTypeId),
                        teacherName, imageName.toString(), System.currentTimeMillis());
                String path = course.getImagePath();
                addCourse(course, container, errorContainer);
                Util.writeFile(path, file);
            }
        } else {
            errorContainer.add("上传图片为空");
        }
    }

//    /**
//     * delete a course and delete the relative contents including files on the disk,
//     * chapters of its, which means comments will be also deleted.
//     *
//     * @param course         the course
//     * @param container      the message container
//     * @param errorContainer the error container
//     * @return true if is successful
//     */
//    public boolean deleteCourse(Course course, List<String> container, List<String> errorContainer) {
//        if (courseDao.getCourseById(course.getCourseId()) == null) {
//            errorContainer.add("课程不存在,无需删除");
//            return false;
//        } else {
//            //delete the image
//            Util.deleteFile(new File(course.getImagePath()));
//            //delete all the chapters
//            List<Chapter> chapters = chapterDao.getChaptersByOwnerCourseId(course.getCourseId());
//            int size = chapters.size();
//            for (int i = 0; i < size; i++) {
//                chapterService.deleteChapter(chapters.get(i));
//            }
//            //delete the course directory
//            Util.deleteFile(new File(course.getCoursePath()));
//            //delete the record
//            courseDao.deleteCourse(course.getCourseId());
//            container.add("删除成功");
//            return true;
//        }
//    }

//    /**
//     * delete a course and delete the relative contents including files on the disk,
//     * chapters of its, which means comments will be also deleted.
//     *
//     * @param course the course
//     * @return true if is successful
//     */
//    public boolean deleteCourse(Course course) {
//        if (courseDao.getCourseById(course.getCourseId()) == null) {
//            return false;
//        } else {
//            //delete the image
//            Util.deleteFile(new File(course.getImagePath()));
//            //delete all the chapters
//            List<Chapter> chapters = chapterDao.getChaptersByOwnerCourseId(course.getCourseId());
//            int size = chapters.size();
//            for (int i = 0; i < size; i++) {
//                chapterService.deleteChapter(chapters.get(i));
//            }
//            //delete the course directory
//            Util.deleteFile(new File(course.getCoursePath()));
//            //delete the learning_record
//            courseLearningRecordService.deleteCourseLearningRecord(courseId);
//            //delete the record
//            courseDao.deleteCourse(course.getCourseId());
//            return true;
//        }
//    }

    /**
     * delete a course and delete the relative contents including files on the disk,
     * chapters of its, which means comments will be also deleted.
     *
     * @param courseId the id of course
     * @return true if is successful
     */
    public boolean deleteCourse(String courseId) {
        Course course;
        if ((course = courseDao.getCourseById(courseId)) == null) {
            return false;
        } else {
            //delete the image
            Util.deleteFile(new File(course.getImagePath()));
            //delete all the chapters
            List<Chapter> chapters = chapterDao.getChaptersByOwnerCourseId(courseId);
            int size = chapters.size();
            for (int i = 0; i < size; i++) {
                chapterService.deleteChapter(chapters.get(i));
            }
            //delete the course directory
            Util.deleteFile(new File(course.getCoursePath()));
            //delete the learning_record
            courseLearningRecordService.deleteRecordsByCourseId(courseId);
            //delete the record
            courseDao.deleteCourse(courseId);
            return true;
        }
    }


    public Course generateCourse(String courseName, String courseTypeId, String teacherName,
                                 String imageName, long currentMillis) {
        Course course = new Course();
        course.setCourseTypeId(courseTypeId);
        course.setCourseName(courseName);
        course.setTeacherName(teacherName);
        course.setCharpterNumber((short) 0);
        course.setCourseCount(0);
        course.setCoursePath(generateCourseRootFilePath(courseName));
        course.setImagePath(generateImageFilePath(course.getCoursePath(), imageName));
        course.setCourseId(generateCourseId(currentMillis));
        return course;
    }

    public String generateCourseId(long currentMillis) {
        StringBuilder builder = new StringBuilder();
        builder.append("course");
        int random = (int) (Integer.MAX_VALUE * Math.random());
        builder.append(Util.convertToNineBits(random));
        builder.append(currentMillis);
        random = (int) (Integer.MAX_VALUE * Math.random());
        return builder.append(Util.convertToNineBits(random)).toString();
    }


    public String generateCourseRootFilePath(String courseName) {
        return rootPath + "/" + courseName;
    }

    public String generateImageFilePath(String coursePath, String imageName) {
        return coursePath + "/" + imageName;
    }
}

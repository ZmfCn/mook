package com.zmf.controller;

import com.zmf.modelProvider.*;
import com.zmf.pojo.Chapter;
import com.zmf.service.CourseLearningRecordService;
import com.zmf.service.CourseService;
import com.zmf.service.EncryptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    private Logger logger = Logger.getLogger(CourseController.class);
    @Autowired
    private MyCoursePageModelProvider myCoursePageModelProvider;
    @Autowired
    private CourseATypeModelProvider courseATypeModelProvider;
    @Autowired
    private CourseAllPageModelProvider courseAllPageModelProvider;
    @Autowired
    private AddCoursePageModelProvider addCoursePageModelProvider;
    @Autowired
    private CoursePageModelProvider coursePageModelProvider;
    @Autowired
    private CourseSearchResultPageModelProvider courseSearchResultPageModelProvider;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseLearningRecordService courseLearningRecordService;


    @RequestMapping(value = "/alterCourse", method = RequestMethod.GET)
    public String alterCoursePage() {
        return "alterCourse";
    }

    @RequestMapping(value = "/alterCourse", method = RequestMethod.POST)
    public String alterCourse(Model model, @RequestParam String preName, @RequestParam String newName, @RequestParam String teacherName,
                              @RequestParam(value = "img", required = false) MultipartFile file) {
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        courseService.alterCourse(messages, errors, file, preName, newName, teacherName);
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "alterCourse";
    }

    @RequestMapping(value = "/delete/{pageNumber}", method = RequestMethod.POST)
    public String deleteCourse(HttpServletRequest request, Model model, @PathVariable int pageNumber) {
        Enumeration<String> enumration = request.getParameterNames();
        String temp;
        logger.info("-------------->delete course");
        while (enumration.hasMoreElements()) {
            temp = enumration.nextElement();
            logger.info("--------> parameter: " + temp + "  value: " + request.getParameter(temp));
            courseService.deleteCourse(encryptionService.decrypt(temp));
        }

        model.addAttribute("pageModel", courseAllPageModelProvider.getPageModel(pageNumber));
        return "courseManagePage";
    }

    @RequestMapping(value = "/typeAll/{encryptedCourseTypeId}/{pageNumber}", method = RequestMethod.GET)
    public String getPageOfACourseType(Model model, @PathVariable String encryptedCourseTypeId,
                                       @PathVariable int pageNumber) {
        logger.info("------------------>encryptedCourseTypeId:" + encryptedCourseTypeId);
        logger.info("------------------>pageNumber:" + pageNumber);
        model.addAttribute("courseATypePageModel",
                courseATypeModelProvider.getPageModel(encryptionService.decrypt(encryptedCourseTypeId),
                        pageNumber));
        return "courseATypePage";
    }


    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addCoursePage(Model model) {
        model.addAttribute("pageModel", addCoursePageModelProvider.getModel());
        return "addCourse";
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addCourse(Model model, @RequestParam(value = "img", required = false) MultipartFile file,
                            @RequestParam String parentTypeId, @RequestParam String courseName,
                            @RequestParam String teacherName) {
        //基本表单
        logger.info("------------------>parentTypeId: " + parentTypeId);
        logger.info("------------------>courseName: " + courseName);
        logger.info("------------------>teacherName: " + teacherName);
        logger.info("------------------>is file empty: " + file.isEmpty());

        //获得物理路径webapp所在路径
//        String pathRoot = request.getSession().getServletContext().getRealPath("");
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();


        courseService.addCourse(messages, errors, file, parentTypeId, courseName, teacherName);

        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        model.addAttribute("pageModel", addCoursePageModelProvider.getModel());
        return "addCourse";
    }


    @RequestMapping(value = "/addToLearn/{encryptedCourseId}", method = RequestMethod.POST)
    public String addToLearn(Model model, HttpSession session, @PathVariable String encryptedCourseId) {
        courseLearningRecordService.addToLearn(encryptionService.decrypt(encryptedCourseId),
                encryptionService.decrypt((String) session.getAttribute("userid")));
        model.addAttribute("coursePageModel", coursePageModelProvider.getModel(encryptionService.decrypt(
                encryptedCourseId), encryptionService.decrypt((String) session.getAttribute("userid"))));
        return "course";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void courseHomePage() {

    }

    @RequestMapping(value = "/course/{encryptedCourseId}", method = RequestMethod.GET)
    public String certainCourse(Model model, HttpSession session, @PathVariable String encryptedCourseId) {
        model.addAttribute("coursePageModel", coursePageModelProvider.getModel(encryptionService.decrypt(
                encryptedCourseId), encryptionService.decrypt((String) session.getAttribute("userid"))));
        return "course";
    }

    @RequestMapping(value = "/all/{pageNumber}", method = RequestMethod.GET)
    public String getAllPages(Model model, @PathVariable int pageNumber) {
        model.addAttribute("pageModel", courseAllPageModelProvider.getPageModel(pageNumber));
        return "courseAllPage";
    }

    @RequestMapping(value = "/myCourse/{pageNumber}", method = RequestMethod.GET)
    public String myCourses(Model model, HttpSession session, @PathVariable int pageNumber) {
        model.addAttribute("myCoursePageModel",
                myCoursePageModelProvider.getModel(encryptionService.decrypt((String) session.getAttribute("userid")),
                        pageNumber));
        return "myCourse";
    }

    @RequestMapping(value = "/search/{pageNumber}", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String string, @PathVariable String pageNumber) {
        model.addAttribute("pageModel", courseSearchResultPageModelProvider.getPageModel(string,
                Integer.parseInt(pageNumber)));
        return "courseSearchResultPage";
    }

    @RequestMapping(value = "/search/{string}/{pageNumber}", method = RequestMethod.GET)
    public String searchPage(Model model, @PathVariable String string, @PathVariable String pageNumber) {
        model.addAttribute("pageModel", courseSearchResultPageModelProvider.getPageModel(string,
                Integer.parseInt(pageNumber)));
        return "courseSearchResultPage";
    }

    @RequestMapping(value = "/manage/{pageNumber}", method = RequestMethod.GET)
    public String getCourseManagePage(Model model, @PathVariable int pageNumber) {
        model.addAttribute("pageModel", courseAllPageModelProvider.getPageModel(pageNumber));
        return "courseManagePage";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    public String manageCourses() {

        return "";
    }
}

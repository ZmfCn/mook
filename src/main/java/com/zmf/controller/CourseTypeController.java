package com.zmf.controller;

import com.zmf.modelProvider.AddCourseTypePageModelProvider;
import com.zmf.modelProvider.CourseTypeViewModelProvider;
import com.zmf.pojo.CourseType;
import com.zmf.service.CourseTypeService;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    private CourseTypeViewModelProvider courseTypeViewModelProvider;
    @Autowired
    private AddCourseTypePageModelProvider addCourseTypePageModelProvider;
    @Autowired
    private CourseTypeService courseTypeService;
    @Autowired
    private EncryptionService encryptionService;

    @RequestMapping(value = "/addType", method = RequestMethod.GET)
    public String getAddType(Model model) {
        model.addAttribute("pageModel", addCourseTypePageModelProvider.getModel());
        return "addCourseTypePage";
    }

    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    public String addType(@RequestParam String topTypeId, @RequestParam String courseTypeName, Model model) {
        model.addAttribute("pageModel", addCourseTypePageModelProvider.getModel());

        CourseType courseType = new CourseType();
        courseType.setTypeName(courseTypeName);
        courseType.setTopTypeId(encryptionService.decrypt(topTypeId));
        courseType.setTypeId(courseTypeService.generateCourseTypeId(System.currentTimeMillis()));

        List<String> messages = new ArrayList<String>();
        List<String> errors = new ArrayList<String>();

        courseTypeService.addACourseType(courseType, messages, errors);

        // send the messages to the view
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "addCourseTypePage";
    }

    @RequestMapping(value = "/deleteType", method = RequestMethod.GET)
    public String getDeleteType() {
        return "deleteCourseTypePage";
    }

    @RequestMapping(value = "/deleteType", method = RequestMethod.POST)
    public String deleteType(@RequestParam String courseTypeId, Model model) {
        List<String> messages = new ArrayList<String>();
        List<String> errors = new ArrayList<String>();
        courseTypeService.deleteACourseType(encryptionService.decrypt(courseTypeId), messages, errors);
        // send the messages to the view
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "deleteCourseTypePage";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String getManageCourseTypePage() {
        return "courseTypeManage";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    public String manageCourseType() {
        return null;
    }

    @RequestMapping(value = "/viewCourseInfo", method = RequestMethod.GET)
    public String viewCourseInformation(Model model) {
        model.addAttribute("pageModel", courseTypeViewModelProvider.getModel());
        return "viewCourseTypeInfo";
    }
}

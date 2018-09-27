package com.zmf.controller;

import com.zmf.modelProvider.CourseTypeStatisticsPageModelProvider;
import com.zmf.modelProvider.CourseViewStatisticsPageModelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-07 22:39
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private CourseTypeStatisticsPageModelProvider courseTypeStatisticsPageModelProvider;
    @Autowired
    private CourseViewStatisticsPageModelProvider courseViewStatisticsPageModelProvider;

    @RequestMapping(value = "/countByType", method = RequestMethod.GET)
    public String countByType(Model model) {
        model.addAttribute("pageModel", courseTypeStatisticsPageModelProvider.getModel());
        return "countByType";
    }

    @RequestMapping(value = "/countByClick", method = RequestMethod.GET)
    public String countByClickTime(Model model) {
        model.addAttribute("pageModel", courseViewStatisticsPageModelProvider.getModel());
        return "countByView";
    }
}

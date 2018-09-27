package com.zmf.controller;

import com.zmf.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-16 16:00
 */
@Controller
@RequestMapping("/keyword")
public class KeywordController {
    @Autowired
    private KeywordService keywordService;

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String keywordManage() {
        return "keywordManage";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewKeywords(Model model) {
        model.addAttribute("keywords", keywordService.getKeywords());
        return "viewKeywords";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addKeyWordPage() {
        return "addKeyword";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addKeyWord(Model model, @RequestParam String keyword) {
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        keywordService.addKeyword(keyword, messages, errors);
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "addKeyword";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteKeyword(Model model, @RequestParam String keyword) {
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        keywordService.deleteKeyword(keyword, messages, errors);
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "deleteKeyword";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteKeywordPage() {
        return "deleteKeyword";
    }
}

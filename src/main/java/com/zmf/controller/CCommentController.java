package com.zmf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ccomment")
public class CCommentController {
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publishCCommet() {


        return "redirect:/log/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCCommet() {


        return "redirect:/log/";
    }
}

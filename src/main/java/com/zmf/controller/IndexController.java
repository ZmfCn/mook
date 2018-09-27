package com.zmf.controller;

import com.zmf.models.IndexPageModel;
import com.zmf.modelProvider.IndexPageModelProvider;
import com.zmf.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
//@RestController
public class IndexController {
    /**
     * the service to create the page model
     */
    @Autowired
    private IndexPageModelProvider indexPageModelProvider;
    @Autowired
    private EncryptionService encryptionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        // pre log in, will be delete before deployment
//        session.setAttribute("username", "zmf");
//        session.setAttribute("permissionCode", "1");
//        session.setAttribute("userid", encryptionService.encrypt("u1984651513065460806"));

        IndexPageModel pageModel = indexPageModelProvider.getModel();
        model.addAttribute("pageModel", pageModel);
        return "index";
//        return "{'a':23}";
    }
}
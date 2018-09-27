package com.zmf.controller;

import com.zmf.modelProvider.CommentCheckPageModelProvider;
import com.zmf.modelProvider.CommentPageModelProvider;
import com.zmf.modelProvider.MyMessagePageModelProvider;
import com.zmf.service.ChapterCommentService;
import com.zmf.service.EncryptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CommentPageModelProvider commentPageModelProvider;
    @Autowired
    private ChapterCommentService chapterCommentService;
    @Autowired
    private CommentCheckPageModelProvider commentCheckPageModelProvider;
    @Autowired
    private MyMessagePageModelProvider myMessagePageModelProvider;
    private Logger logger = Logger.getLogger(CommentController.class);


    @RequestMapping(value = "/my/delete/{encryptedCommentId}/{pageNumber}", method = RequestMethod.POST)
    public String deleteComment(Model model, @PathVariable String pageNumber,
                                @PathVariable String encryptedCommentId, HttpSession session) {
        chapterCommentService.deleteChapterComment(encryptionService.decrypt(encryptedCommentId));
        model.addAttribute("pageModel", myMessagePageModelProvider.getModel(
                encryptionService.decrypt((String) session.getAttribute("userid")), Integer.parseInt(pageNumber)));
        return "myMessage";
    }

    @RequestMapping(value = "/my/{pageNumber}", method = RequestMethod.GET)
    public String myCommentsPage(Model model, @PathVariable String pageNumber, HttpSession session) {
        model.addAttribute("pageModel", myMessagePageModelProvider.getModel(
                encryptionService.decrypt((String) session.getAttribute("userid")), Integer.parseInt(pageNumber)));
        return "myMessage";
    }

    @RequestMapping(value = "/publish/{encryptedChapterId}/{pageNumber}", method = RequestMethod.POST)
    public String publishComment(Model model, @PathVariable String encryptedChapterId,
                                 @PathVariable String pageNumber, HttpSession session, @RequestParam String content) {
        chapterCommentService.addChapterComment(encryptionService.decrypt((String) session.getAttribute("userid")),
                encryptionService.decrypt(encryptedChapterId), content);
        model.addAttribute("pageModel", commentPageModelProvider.getModel(encryptionService.decrypt(encryptedChapterId),
                Integer.parseInt(pageNumber)));
        return "comment";
    }

    @RequestMapping(value = "/{encryptedChapterId}/{pageNumber}", method = RequestMethod.GET)
    public String commentPage(Model model, @PathVariable String encryptedChapterId,
                              @PathVariable String pageNumber) {
        model.addAttribute("pageModel", commentPageModelProvider.getModel(encryptionService.decrypt(encryptedChapterId),
                Integer.parseInt(pageNumber)));
        return "comment";
    }

    @RequestMapping(value = "/delete/{encryptedCommentId}/{encryptedChapterId}/{pageNumber}", method = RequestMethod.POST)
    public String deleteComment(Model model, @PathVariable String encryptedChapterId,
                                @PathVariable String pageNumber, @PathVariable String encryptedCommentId) {
        chapterCommentService.deleteChapterComment(encryptionService.decrypt(encryptedCommentId));
        model.addAttribute("pageModel", commentPageModelProvider.getModel(encryptionService.decrypt(encryptedChapterId),
                Integer.parseInt(pageNumber)));
        return "comment";
    }

    @RequestMapping(value = "/manage/{pageNumber}", method = RequestMethod.GET)
    public String getManageCommentsPage(Model model, @PathVariable String pageNumber) {
        model.addAttribute("pageModel", commentCheckPageModelProvider.getModel(Integer.parseInt(pageNumber)));
        return "commentManage";
    }

    @RequestMapping(value = "/manage/{pageNumber}", method = RequestMethod.POST)
    public String manageComments(Model model, @PathVariable String pageNumber, HttpServletRequest request) {
        Enumeration<String> enumration = request.getParameterNames();
        String temp;
        logger.info("-------------->check comment");
        while (enumration.hasMoreElements()) {
            temp = enumration.nextElement();
            logger.info("--------> parameter: " + temp + "  value: " + request.getParameter(temp));
            String commentId = encryptionService.decrypt(temp);
            chapterCommentService.setCommmentIsChecked(commentId, true);
            chapterCommentService.setCommmentAbandoned(commentId, !Boolean.valueOf(request.getParameter(temp)));
        }

        model.addAttribute("pageModel", commentCheckPageModelProvider.getModel(Integer.parseInt(pageNumber)));
        return "commentManage";
    }
}

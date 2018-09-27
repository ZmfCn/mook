package com.zmf.controller;

import com.zmf.modelProvider.CatalogModelProvider;
import com.zmf.modelProvider.ChapterPageModelProvider;
import com.zmf.modelProvider.CoursePageModelProvider;
import com.zmf.models.ChapterPageModel;
import com.zmf.pojo.Chapter;
import com.zmf.service.ChapterService;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/chapter")
@Controller
public class ChapterController {
    private Logger logger = Logger.getLogger(ChapterController.class);
    @Autowired
    private ChapterPageModelProvider chapterPageModelProvider;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CoursePageModelProvider coursePageModelProvider;
    @Autowired
    private CatalogModelProvider catalogModelProvider;

    @RequestMapping(value = "/chapter/{encryptedChapterId}", method = RequestMethod.GET)
    public String getChapterPage(@PathVariable String encryptedChapterId, Model model) {
        ChapterPageModel chapterPageModel = chapterPageModelProvider.getModel(encryptionService.decrypt(encryptedChapterId));
        model.addAttribute("pageModel", chapterPageModel);
        courseService.addCount(encryptionService.decrypt(chapterPageModel.getChapter().getOwnerCourseId()));
        if (chapterPageModel.getChapter().getIsPdf()) {
            return "pdf";
        } else {
            return "chapter";
        }
    }

    @RequestMapping(value = "/addChapter/{encryptedCourseId}", method = RequestMethod.GET)
    public String addChapterPage(Model model, @PathVariable String encryptedCourseId) {
        model.addAttribute("courseId", encryptedCourseId);
        return "addChapter";
    }


    @RequestMapping(value = "/alterChapter/{encryptedChapterId}", method = RequestMethod.GET)
    public String alterChapterPage(Model model, @PathVariable String encryptedChapterId) {
        Chapter chapter = chapterService.getChapterById(encryptionService.decrypt(encryptedChapterId));
        chapter.setChapterId(encryptedChapterId);
        model.addAttribute("pageModel", chapter);
        return "alterChapter";
    }

    @RequestMapping(value = "/alterChapter/{encryptedChapterId}", method = RequestMethod.POST)
    public String alterChapter(Model model, @PathVariable String encryptedChapterId,
                               @RequestParam String order, @RequestParam String chapterName,
                               @RequestParam(value = "file", required = false) MultipartFile file) {
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        chapterService.alterChapter(messages, errors, encryptionService.decrypt(encryptedChapterId),
                order, chapterName, file);
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        Chapter chapter = chapterService.getChapterById(encryptionService.decrypt(encryptedChapterId));
        chapter.setChapterId(encryptedChapterId);
        model.addAttribute("pageModel", chapter);
        return "alterChapter";
    }

    @RequestMapping(value = "/addChapter/{encryptedCourseId}", method = RequestMethod.POST)
    public String addChapter(Model model, @PathVariable String encryptedCourseId,
                             @RequestParam String order, @RequestParam String chapterName,
                             @RequestParam(value = "file", required = false) MultipartFile file) {
        logger.info("---------------->encrypted course id: " + encryptedCourseId);
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        chapterService.addChapter(messages, errors, encryptedCourseId, order, chapterName, file);

        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        model.addAttribute("courseId", encryptedCourseId);
        return "addChapter";
    }

    @RequestMapping(value = "/deleteChapter/{encryptedCourseId}/{encryptedChapterId}", method = RequestMethod.POST)
    public String deleteChapter(@PathVariable String encryptedCourseId, @PathVariable String encryptedChapterId,
                                Model model, HttpSession session) {
        chapterService.deleteChapter(encryptionService.decrypt(encryptedChapterId));
        model.addAttribute("coursePageModel", coursePageModelProvider.getModel(encryptionService.decrypt(
                encryptedCourseId), encryptionService.decrypt((String) session.getAttribute("userid"))));
        return "course";
    }

    @RequestMapping(value = "/catalog/{encryptedCourseId}", method = RequestMethod.GET)
    public String catalogPage(Model model, @PathVariable String encryptedCourseId) {
        model.addAttribute("pageModel", catalogModelProvider.getModel(encryptionService.decrypt(encryptedCourseId)));
        return "catalog";
    }
}

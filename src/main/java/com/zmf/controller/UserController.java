package com.zmf.controller;

import com.zmf.Utils.Util;
import com.zmf.modelProvider.ViewUserPermissionPageModelProvider;
import com.zmf.pojo.User;
import com.zmf.service.EmailService;
import com.zmf.service.EncryptionService;
import com.zmf.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private ViewUserPermissionPageModelProvider viewUserPermissionPageModelProvider;
    private Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/alterPassword", method = RequestMethod.GET)
    public String alterPasswordPage() {
        return "modifyPassword";
    }

    @RequestMapping(value = "/alterPassword", method = RequestMethod.POST)
    public String alterPassword(Model model, @RequestParam String username, @RequestParam String prePassword,
                                @RequestParam String newPassword) {
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        userService.updatePasswordByName(username, prePassword, newPassword, messages, errors);
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "modifyPassword";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String anthentication(@Validated User user, BindingResult result, HttpSession session, Model model) {
        logger.info("-------------------> /login:" + user.getUserName());

        //获取校验错误信息
        if (result.hasErrors()) {
            // 输出错误信息
            List<ObjectError> allErrors = result.getAllErrors();

            // 将错误信息传到页面
            model.addAttribute("allErrors", allErrors);

            //可以直接使用model将提交pojo回显到页面
            model.addAttribute("user", user);

            // 出错重新到登录页面
            return "login2";
        }

        List<String> errors = new ArrayList<>();

        if (userService.isValid(user, errors)) {
            session.setAttribute("username", user.getUserName());
            session.setAttribute("permissionCode", user.getRoleType());
            session.setAttribute("userid", encryptionService.encrypt(user.getUserId()));
            return "redirect:/";
        }


        // 将错误信息传到页面
        model.addAttribute("errors", errors);

        //可以直接使用model将提交pojo回显到页面
        model.addAttribute("user", user);
        return "login2";
    }

    /**
     * to activate an account
     */
    @RequestMapping("/confirm/{encryptedId}")
    public String confirm(HttpServletRequest request, Model model, @PathVariable String encryptedId) {
        logger.info("------------------->encrpted id: " + encryptedId);
        // get the origin user id
        String userId = encryptionService.decrypt(encryptedId);
        logger.info("-------------------> confirm:userid: " + userId);

        // confirm to activate the account
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        userService.confirmUser(userId, messages, errors);

        // send the messages to the view
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);

        // show the view
        return "activate";
    }

    @RequestMapping(value = "/viewUserPermission", method = RequestMethod.GET)
    public String viewUserPermission(Model model) {
        model.addAttribute("pageModel", viewUserPermissionPageModelProvider.getModel());
        return "viewUserPermissionPage";
    }

    @RequestMapping(value = "/modifyUserPermission", method = RequestMethod.GET)
    public String modifyUserPermissionPage(Model model) {
        model.addAttribute("pageModel", viewUserPermissionPageModelProvider.getModel());
        return "modifyUserPermissionPage";
    }

    @RequestMapping(value = "/modifyUserPermission", method = RequestMethod.POST)
    public String modifyUserPermission(HttpServletRequest request, Model model) {
        logger.info("----------------------->query string: " + request.getParameterNames().nextElement());
        Enumeration<String> parameterNames = request.getParameterNames();
        String temp;
        while (parameterNames.hasMoreElements()) {
            temp = parameterNames.nextElement();
            userService.updateUserCommentPermission(encryptionService.decrypt(temp),
                    !Boolean.valueOf(request.getParameter(temp)));
        }

        // confirm to activate the account
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        messages.add("修改成功");

        // send the messages to the view
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        model.addAttribute("pageModel", viewUserPermissionPageModelProvider.getModel());
        return "modifyUserPermissionPage";
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String getUserManagePage() {
        return "userManagePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "login2";
    }


    /**
     * log out
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        model.addAttribute("message", "hello");
        return "register2";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Validated User user, BindingResult result, Model model) {
        // get the validation message
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            model.addAttribute("allErrors", allErrors);
            model.addAttribute("user", user);
            return "register2";
        }

        // if no error exist, continue to do this
        long currentMillisec = System.currentTimeMillis();
        user.setUserId(userService.generateUid(user.getEmail(), currentMillisec));

        // add the user into the database
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        if (userService.addUser(user, messages, errors)) {
            // generate a confirm link
            String encrytedId = encryptionService.encrypt(user.getUserId());
            String confirmLink = userService.generateConfirmLink(encrytedId);

            // send the link to the user email
            try {
                emailService.sendEmail(user.getEmail(), "<h2>点击链接以激活账号</h2>", confirmLink);
            } catch (Exception e) {
                errors.add("邮件发送失败,请确认邮箱的真实性和有效性,或稍后注册");
                model.addAttribute("messages", messages);
                model.addAttribute("errors", errors);
                return "register2";
            }
            // else is handled by the certain controller
            messages.add("邮件发送成功,请点击邮箱中的链接激活账号");
        }
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "register2";
    }


    @RequestMapping(value = "/seekPassword", method = RequestMethod.GET)
    public String seekPasswordPage() {
        return "seekPassword";
    }

    @RequestMapping(value = "/seekPassword", method = RequestMethod.POST)
    public String seekPasswordAuthentication(Model model, @RequestParam String email,
                                             @RequestParam String newPassword) {
        List<String> messages = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        if (Util.isEmpty(email)) {
            errors.add("邮箱不能为空");
        } else {
            if (!email.contains("@")) {
                errors.add("邮箱无效");
            } else {
                if (Util.isEmpty(newPassword) || !userService.isPasswordValid(newPassword)) {
                    errors.add("密码是1-15位之间的普通字符");
                } else {
                    String encryptedEmail = encryptionService.encrypt(email);
                    String encryptedPassword = encryptionService.encrypt(newPassword);
                    String confirmLink = userService.generateUserPasswordConfirmLink(encryptedEmail, encryptedPassword);

                    // send the link to the user email
                    try {
                        emailService.sendEmail(email, "<h2>点击链接以完成修改密码</h2>", confirmLink);
                        messages.add("邮件发送成功,请点击邮箱中的链接以完成修改密码");
                    } catch (Exception e) {
                        errors.add("邮件发送失败,请确认邮箱的真实性和有效性");
                    }
                }
            }
        }
        model.addAttribute("messages", messages);
        model.addAttribute("errors", errors);
        return "seekPassword";
    }

    @RequestMapping(value = "/seek/{encryptedEmail}/{encryptedPassword}", method = RequestMethod.GET)
    public String confirmSeekPassword(Model model, @PathVariable String encryptedEmail,
                                      @PathVariable String encryptedPassword) {
        userService.updatePasswordByEmail(encryptionService.decrypt(encryptedEmail),
                encryptionService.decrypt(encryptedPassword), new ArrayList<>(), new ArrayList<>());
        model.addAttribute("username", userService.getUserNameByEmail(encryptionService.decrypt(encryptedEmail)));
        return "seekSuccessful";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUserInformation() {

        return "";
    }

    @RequestMapping(value = "/learning", method = RequestMethod.GET)
    public void userLearningInformation() {

    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public void getUserMessagePage() {

    }

    @RequestMapping(value = "/deleteUnconfirmed", method = RequestMethod.GET)
    public String deleteUnconfirmed() {
        userService.deleteAllUnconfirmed();
        return "index";
    }
}

package com.lxy.controller;

import com.lxy.pojo.User;
import com.lxy.service.BlogService;
import com.lxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author lxy
 * @Description
 * @Date 2022/12/5 14:00
 */

@Controller
@RequestMapping("/")
public class AboutController {


    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @Value("${frontNewSize}")
    private Integer newSize;

    @GetMapping("about")
    public String about(Model model){
        User user = userService.getUser();
        model.addAttribute("user", user);
        model.addAttribute("newBlogs", blogService.getByTime(newSize));
        return "about";
    }

}

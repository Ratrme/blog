package com.lxy.controller;

import com.lxy.mapper.BlogMapper;
import com.lxy.pojo.Blog;
import com.lxy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


/**
 * @Author lxy
 * @Description
 * @Date 2022/12/5 15:42
 */
@Controller
@RequestMapping("/")
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogMapper blogMapper;


    @Value("${frontNewSize}")
    private Integer newSize;

    @GetMapping("archives")
    public String archives(Model model){
        Map<String, List<Blog>> map = blogService.archivesBlog();
        model.addAttribute("newBlogs", blogService.getByTime(newSize));
        model.addAttribute("blogMap", map);
        model.addAttribute("count",blogMapper.getCount());
        return "archives";
    }
}

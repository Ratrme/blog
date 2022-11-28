package com.lxy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.pojo.Blog;
import com.lxy.service.BlogService;
import com.lxy.service.TagService;
import com.lxy.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Value("${frontTypeSize}")
    private Integer typeSize;

    @Value("${frontTagSize}")
    private Integer tagSize;

    @Value("${frontHotSize}")
    private Integer hotSize;

    @Value("${frontNewSize}")
    private Integer newSize;

    @Value("${frontNewRecommend}")
    private Integer newRecommend;
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , Model model) {
        PageHelper.startPage(pageNum, 6);
        List<Blog> allBlog = blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("types", typeService.getFrontTypes(typeSize));
        model.addAttribute("tags", tagService.getFrontTags(tagSize));
        model.addAttribute("hotBlogs", blogService.getViews(hotSize));
        model.addAttribute("newRecommendBlogs", blogService.getByTimeRecommend(newRecommend));
        model.addAttribute("newBlogs",blogService.getByTime(newSize));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable String id){
        return "blog";
    }
}

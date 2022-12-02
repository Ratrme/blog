package com.lxy.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.pojo.Blog;
import com.lxy.pojo.Tag;
import com.lxy.pojo.User;
import com.lxy.service.BlogService;
import com.lxy.service.TagService;
import com.lxy.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @Value("${frontHotSize}")
    private Integer hotSize;

    @Value("${frontNewSize}")
    private Integer newSize;

    @Value("${frontNewRecommend}")
    private Integer newRecommend;
    @GetMapping()
    public String index(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , Model model) {
        PageHelper.startPage(pageNum, 8);
        List<Blog> allBlog = blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("types", typeService.getFrontTypes());
        model.addAttribute("tags", tagService.getFrontTags());
        model.addAttribute("hotBlogs", blogService.getViews(hotSize));
        model.addAttribute("newRecommendBlogs", blogService.getByTimeRecommend(newRecommend));
        model.addAttribute("newBlogs",blogService.getByTime(newSize));
        return "index";
    }

    @PostMapping("search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            ,@RequestParam String query, Model model){
        List<Blog> allBlog = blogService.getBySearch(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("query",query);
        model.addAttribute("newBlogs",blogService.getByTime(newSize));
        return "search";
    }
    
    @GetMapping("blog/{id}")
    public String blog(@PathVariable Long id, Model model,HttpSession session){
        Blog blogById = blogService.getBlogByIdFront(id);
        blogService.addViews(id);
        String tagIds = blogById.getTagIds();
        List<Tag> allTags = tagService.getAllTags(tagIds);
        User user = (User) session.getAttribute("user");
        model.addAttribute("blog",blogById);
        model.addAttribute("tags",allTags);
        model.addAttribute("blogUser",user);
        model.addAttribute("newBlogs",blogService.getByTime(newSize));
        return "blog";
    }
}

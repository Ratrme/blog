package com.lxy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.mapper.BlogMapper;
import com.lxy.mapper.TagMapper;
import com.lxy.pojo.Blog;
import com.lxy.pojo.Tag;
import com.lxy.service.BlogService;
import com.lxy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author lxy
 * @Description
 * @Date 2022/12/4 13:52
 */

@Controller
@RequestMapping("/")
public class TagFrontController {

    @Autowired
    private TagService tagService;


    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogMapper blogMapper;

    @Value("${frontNewSize}")
    private Integer newSize;

    @GetMapping("tags/{id}")
    public String tags(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , Model model, @PathVariable Long id){
        PageHelper.startPage(pageNum, 8);
        List<Tag> frontTags = tagService.getFrontTags();
        if (id == -1){
            id = frontTags.get(0).getId();
        }
        List<Blog> byTagId = blogMapper.getByTagId(id);
        model.addAttribute("tags", frontTags);
        PageInfo<Blog> pageInfo = new PageInfo<>(byTagId);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("activeTagId",id);
        model.addAttribute("newBlogs", blogService.getByTime(newSize));
        return "tags";
    }
}

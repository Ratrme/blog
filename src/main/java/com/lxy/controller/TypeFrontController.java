package com.lxy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.mapper.TypeMapper;
import com.lxy.pojo.Blog;
import com.lxy.pojo.Type;
import com.lxy.service.BlogService;
import com.lxy.service.TypeService;
import com.lxy.vo.BlogQuery;
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
 * @Date 2022/12/4 11:48
 */

@Controller
@RequestMapping("/")
public class TypeFrontController {

    @Autowired
    private TypeService typeService;


    @Autowired
    private BlogService blogService;

    @Value("${frontNewSize}")
    private Integer newSize;

    @GetMapping("types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , Model model, @PathVariable Long id){
        PageHelper.startPage(pageNum, 8);
        List<Type> allType = typeService.getFrontTypes();
        if (id == -1){
            id = allType.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        List<Blog> blogsByTypeId = blogService.getBlogsBySearchInfo(blogQuery);
        System.out.println(allType);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogsByTypeId);
        model.addAttribute("types",allType);
        model.addAttribute("blogs",pageInfo);
        model.addAttribute("activeTypeId",id);
        model.addAttribute("newBlogs", blogService.getByTime(newSize));
        return "types";
    }
}

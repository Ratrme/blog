package com.lxy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.pojo.Blog;
import com.lxy.pojo.Tag;
import com.lxy.pojo.User;
import com.lxy.service.BlogService;
import com.lxy.service.TagService;
import com.lxy.service.TypeService;
import com.lxy.utils.StingToList;
import com.lxy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin")
public class BlogsController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , BlogQuery blogQuery, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = blogService.getBlogsBySearchInfo(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum
            , BlogQuery blogQuery, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> allBlog = blogService.getBlogsBySearchInfo(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTags());
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }


    @PostMapping("/blogs/save")
    public String saveBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            blog.setUser(user);
            blog.setType(typeService.getType(blog.getType().getId()));
            blog.setTags(tagService.getAllTags(blog.getTagIds()));
            int save = blogService.saveBlog(blog);
            if (save > 0) {
                attributes.addFlashAttribute("message", "新增成功！");
            } else {
                attributes.addFlashAttribute("error", "新增失败！");
            }
            return "redirect:admin/blogs";
        } else {
            return "redirect:admin/index";
        }
    }



    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.getAllTags(blog.getTagIds()));
        int update = blogService.updateBlog(blog);
        if (update > 0) {
            attributes.addFlashAttribute("message", "修改成功！");
        } else {
            attributes.addFlashAttribute("error", "修改失败！");
        }
        return "redirect:admin/blogs";
    }


    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes){
        int i = blogService.deleteBlog(id);
        if (i > 0) {
            attributes.addFlashAttribute("message", "删除成功！");
        } else {
            attributes.addFlashAttribute("error", "删除失败！");
        }
        return "redirect:admin/blogs";
    }
}

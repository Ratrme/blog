package com.lxy.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.pojo.Tag;
import com.lxy.pojo.Type;
import com.lxy.service.TagService;
import com.lxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {


    @Autowired
    private TagService tagService;


    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum
            , Model model) throws ParseException {
        PageHelper.startPage(pageNum, 5);
        List<Tag> allTag = tagService.getAllTags();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "/admin/tags-input";
    }

    @GetMapping("/tags/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "/admin/tags-input";
    }


    @PostMapping("/tags")
    public String savePost(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            result.rejectValue("name", "nameError","已有该标签，请勿重复添加！");
        }
        if (result.hasErrors()) {
            return "/admin/tags-input";
        }
        int i = tagService.saveTag(tag);
        if (i == 1) {
            attributes.addFlashAttribute("message","标签添加成功！");
        } else {
            attributes.addFlashAttribute("error","标签添加失败！");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/update/{id}")
    public String updatePost(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            result.rejectValue("name", "nameError","已有该标签，请勿重复添加！");
        }
        if (result.hasErrors()) {
            return "/admin/tags-input";
        }
        int i = tagService.updateTag(tag);
        if (i == 1) {
            attributes.addFlashAttribute("message","标签修改成功！");
        } else {
            attributes.addFlashAttribute("error","标签修改失败！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","标签删除成功！");
        return "redirect:/admin/tags";
    }

}

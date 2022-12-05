package com.lxy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxy.pojo.Type;
import com.lxy.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum
            ,Model model) throws ParseException {
        PageHelper.startPage(pageNum, 5);
        List<Type> allType = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }


    @PostMapping("/types")
    public String savePost(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            result.rejectValue("name", "nameError","已有该分类，请勿重复添加！");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        int i = typeService.saveType(type);
        if (i == 1) {
            attributes.addFlashAttribute("message","分类添加成功！");
        } else {
            attributes.addFlashAttribute("error","分类添加失败！");
        }
        return "redirect:admin/types";
    }

    @PostMapping("/types/update/{id}")
    public String updatePost(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            result.rejectValue("name", "nameError","已有该分类，请勿重复添加！");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        int i = typeService.updateType(type);
        if (i == 1) {
            attributes.addFlashAttribute("message","分类修改成功！");
        } else {
            attributes.addFlashAttribute("error","分类修改失败！");
        }
        return "redirect:admin/types";
    }

    @GetMapping("/types/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","分类删除成功！");
        return "redirect:admin/types";
    }
}

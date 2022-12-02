package com.lxy.controller;

import com.lxy.pojo.Blog;
import com.lxy.pojo.Comment;
import com.lxy.pojo.User;
import com.lxy.service.BlogService;
import com.lxy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${commentAvatar}")
    private String avatar;

    @GetMapping("comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> commentsByBlogId = commentService.getCommentsByBlogId(blogId);
        model.addAttribute("comments", commentsByBlogId);
        return "blog :: commentList";
    }


    @PostMapping("comment")
    public String postShow(Comment comment, Model model){
        Long id = comment.getBlog().getId();
        List<Comment> commentsByBlogId = commentService.getCommentsByBlogId(id);
        model.addAttribute("comments", commentsByBlogId);
        return "blog :: commentList";
    }

    @PostMapping("comments")
    public String postSave(Comment comment, HttpSession session){
        Long id = comment.getBlog().getId();
        Blog blogById = blogService.getBlogById(id);
        comment.setBlog(blogById);
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setNickname(user.getNickname());
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(user.getType() == 1);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + id;
    }
}

package com.lxy.service.impl;

import com.lxy.mapper.BlogMapper;
import com.lxy.mapper.BlogTagsMapper;
import com.lxy.mapper.TagMapper;
import com.lxy.pojo.Blog;
import com.lxy.pojo.Tag;
import com.lxy.service.BlogService;
import com.lxy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private BlogTagsMapper blogTagsMapper;


    @Override
    public Blog getBlogById(Long id) {
        Blog blogById = blogMapper.getBlogById(id);
        List<Long> longs = blogTagsMapper.selectByBlogId(id);
        List<Tag> allTags = tagMapper.getAllTags(longs);
        String tags = tagsToIds(allTags);
        blogById.setTagIds(tags);
        return blogById;
    }

    @Override
    public int addViews(Long id) {
        return 0;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<Blog> getViews(Integer size) {
        return blogMapper.getByViews(size);
    }

    @Override
    public List<Blog> getByTime(Integer size) {
        return blogMapper.getByTime(size);
    }

    @Override
    public List<Blog> getByTimeRecommend(Integer size) {
        return blogMapper.getByTimeRecommend(size);
    }


    @Override
    public List<Blog> getBlogsBySearchInfo(BlogQuery searchInfo) {
        return blogMapper.getBlogsBySearchInfo(searchInfo);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setViews(0);
        int i = blogMapper.saveBlog(blog);
        handlerBlogTag(blog.getId(), blog.getTags());
        return i;
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        int i = blogMapper.updateBlog(blog);
        blogTagsMapper.delete(blog.getId());
        handlerBlogTag(blog.getId(), blog.getTags());
        return i;
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public Long getBlogNums() {
        return null;
    }


    public void handlerBlogTag(Long blogId, List<Tag> tags) {
        for (Tag tag : tags) {
             blogTagsMapper.insertBlogTag(blogId, tag.getId());
        }
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return null;
        }
    }


}

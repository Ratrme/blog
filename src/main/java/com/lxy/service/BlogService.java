package com.lxy.service;

import com.lxy.pojo.Blog;
import com.lxy.vo.BlogQuery;

import java.util.List;

public interface BlogService {


    //3、查询：（博客ID） 查询博客信息
    Blog getBlogById(Long id);

    //7、更新：通过（博客id）， 更新博客（访问次数+1）
    int addViews(Long id);


    List<Blog> getAllBlog();

    List<Blog> getViews(Integer size);

    List<Blog> getByTime(Integer size);

    List<Blog> getByTimeRecommend(Integer size);

    //8、查询：通过Search对象（title、typeName, recommend）, 查询博客信息
    List<Blog> getBlogsBySearchInfo(BlogQuery searchInfo);

    //9、添加: 通过Blog对象, 并返回博客（自增ID）
    int saveBlog(Blog blog);

    //10、更新：通过Blog对象
    int updateBlog(Blog blog);

    //11、删除博客：通过blogId
    int deleteBlog(Long id);


    //15、查询：博客总数目
    Long getBlogNums();

}

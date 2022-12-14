package com.lxy.mapper;

import com.lxy.pojo.Blog;
import com.lxy.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {


    //3、查询：（博客ID） 查询博客信息
    Blog getBlogById(Long id);


    Blog getBlogByIdFront(Long id);

    //4、查询所有的博客
    List<Blog> getAllBlog();


    int addViews(Blog blog);

    List<Blog> getByViews(Integer size);

    List<Blog> getByTime(Integer size);

    List<Blog> getByTimeRecommend(Integer size);

    List<Blog> getBySearch(String query);

    //8、查询：通过Search对象（title、typeName, recommend）, 查询博客信息
    List<Blog> getBlogsBySearchInfo(BlogQuery searchInfo);

    List<String> getYears();

    List<Blog> getByYear(String year);

    //9、添加: 通过Blog对象, 并返回博客（自增ID）
    int saveBlog(Blog blog);


    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    List<Blog> getByTagId(Long id);

    int getCount();
}

package com.xue.demo.comment.mapper;

import java.util.List;

import com.xue.demo.comment.model.Blog;
import com.xue.demo.comment.model.LimitOffset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface BlogMapper {
  
  public List<Blog> selectBlog(@Param("blog") Blog blog, @Param("pageParams") LimitOffset limitOffset);
  
  public Long selectBlogCount(Blog query);

}

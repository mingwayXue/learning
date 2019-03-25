package com.xue.demo.comment.controller;

import java.util.List;

import com.xue.demo.comment.common.RestResponse;
import com.xue.demo.comment.model.Blog;
import com.xue.demo.comment.model.BlogQueryReq;
import com.xue.demo.comment.model.ListResponse;
import com.xue.demo.comment.service.BlogService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("blog")
public class BlogController {
  
  @Autowired
  private BlogService blogService;
  
  @RequestMapping("list")
  public RestResponse<ListResponse<Blog>> list(@RequestBody BlogQueryReq req){
    Pair<List<Blog>,Long> pair = blogService.queryBlog(req.getBlog(),req.getLimit(),req.getOffset());
    return RestResponse.success(ListResponse.build(pair.getKey(), pair.getValue()));
  }
  
  @RequestMapping("one")
  public RestResponse<Blog> one(Integer id){
    Blog blog = blogService.queryOneBlog(id);
    return RestResponse.success(blog);
  }

}
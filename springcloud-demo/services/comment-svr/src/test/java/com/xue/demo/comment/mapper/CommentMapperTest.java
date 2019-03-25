package com.xue.demo.comment.mapper;

import com.xue.demo.comment.CommentSvrApplicationTests;
import com.xue.demo.comment.model.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2018-10-16 18:02.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class CommentMapperTest extends CommentSvrApplicationTests{

	@Autowired
	private CommentMapper commentMapper;

	@Test
	public void selectComments() throws Exception {
		List<Comment> comments = commentMapper.selectComments(1, 3);
		System.out.println(comments);
	}

}
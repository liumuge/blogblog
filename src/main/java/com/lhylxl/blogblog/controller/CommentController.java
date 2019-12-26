package com.lhylxl.blogblog.controller;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.domain.Comment;
import com.lhylxl.blogblog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/26
 * @time: 15:16
 * @description:
 */
@RestController
@Api(value = "comment管理接口", description = "添加评论，审核评论,管理评论")
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/addComment")
	@ApiOperation("添加评论")
	public QueryResponseResult addComment(@RequestBody Comment comment){
		return commentService.addComment(comment);
	}

	@GetMapping("/findAll")
	@ApiOperation("获取全部评论")
	public QueryResponseResult findAll(Integer currentPage, Integer pageSize,Integer uId){
		return commentService.findAll(currentPage,pageSize,uId);
	}

	@GetMapping("/reviewComment")
	@ApiOperation("审核评论")
	public QueryResponseResult reviewComment(Integer commentId){
		return commentService.reviewComment(commentId);
	}

	@GetMapping("/removeComment")
	@ApiOperation("删除评论")
	public QueryResponseResult removeComment(Integer commentId){
		return commentService.removeComment(commentId);
	}



}

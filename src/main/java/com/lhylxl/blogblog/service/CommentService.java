package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.CommonCode;
import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.common.model.response.QueryResult;
import com.lhylxl.blogblog.common.utils.Page;
import com.lhylxl.blogblog.domain.Article;
import com.lhylxl.blogblog.domain.Comment;
import com.lhylxl.blogblog.mapper.ArticleMapper;
import com.lhylxl.blogblog.mapper.CommentMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/26
 * @time: 15:07
 * @description:
 */
@Service
public class CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Autowired
	ArticleMapper articleMapper;

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public QueryResponseResult addComment(Comment comment) {
		if (comment!=null) {
			comment.setCreatTime(new Date());
			int i = commentMapper.insert(comment);
			QueryResult<Comment> result = new QueryResult<>();
			ArrayList<Comment> comments = new ArrayList<>();
			comments.add(comment);
			result.setList(comments);
			result.setTotal(comments.size());
			return new QueryResponseResult(CommonCode.SUCCESS, result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM, null);
	}

	/**
	 * 查找全部评论
	 * @param currentPage
	 * @param pageSize
	 * @param uId
	 * @return
	 */
	public QueryResponseResult findAll(Integer currentPage, Integer pageSize, Integer uId){
		if (uId!=null){
			if (pageSize == null) {
				pageSize = 5;
			}
			List<Comment> comments = commentMapper.findAll(uId);
			//分页
			Page commentPage = new Page(currentPage, pageSize, comments.size());
			//查询的分页数据
			List<Comment> pageComment = commentMapper.findAllComment(commentPage, uId);
			for (Comment comment : pageComment) {
				comment.setArticleTitle(articleMapper.findByArticleIdTitle(comment.getArticleId()));
			}
			commentPage.setList(pageComment);
			QueryResult<Page> result = new QueryResult<>();
			ArrayList<Page> list = new ArrayList<>();
			list.add(commentPage);
			result.setTotal(pageComment.size());
			result.setList(list);
			return new QueryResponseResult(CommonCode.SUCCESS, result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}

	/**
	 * 审核评论
	 * @param id
	 * @return
	 */
	public QueryResponseResult reviewComment(Integer id){
		if(id!=null){
			int i = commentMapper.updateComment(id);
			if(i==1){
				return new QueryResponseResult(CommonCode.SUCCESS,null);
			}
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}

	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public QueryResponseResult removeComment(Integer id){
		if(id!=null){
			int i = commentMapper.deleteComment(id);
			if(i==1){
				return new QueryResponseResult(CommonCode.SUCCESS,null);
			}
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}
}

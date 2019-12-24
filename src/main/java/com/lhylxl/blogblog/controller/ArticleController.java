package com.lhylxl.blogblog.controller;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.domain.Article;
import com.lhylxl.blogblog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/24
 * @time: 10:13
 * @description:
 */
@RestController
@Api(value="article管理接口",description = "文章保存，文章删除,查找")
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@PostMapping("/addArticle")
	@ApiOperation("添加文章")
	public QueryResponseResult addArticle(@RequestBody Article article){
		return articleService.addArticle(article);
	}

	@GetMapping("/getArticleList")
	@ApiOperation("获取文章列表")
	public QueryResponseResult findList(Integer currentPage, Integer pageSize,Integer uId,Integer status){
		return articleService.findList(currentPage, pageSize, uId,status);
	}
}

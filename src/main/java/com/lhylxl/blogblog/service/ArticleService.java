package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.CommonCode;
import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.common.model.response.QueryResult;
import com.lhylxl.blogblog.common.utils.Page;
import com.lhylxl.blogblog.domain.Article;
import com.lhylxl.blogblog.domain.Tag;
import com.lhylxl.blogblog.mapper.ArticleMapper;
import io.swagger.annotations.Example;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 17:55
 * @description:
 */
@Service
public class ArticleService {

	@Autowired
	ArticleMapper articleMapper;

	/**
	 * 查询文章并分页
	 *
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public QueryResponseResult findList(Integer currentPage, Integer pageSize,Integer uId,Integer status) {
		if (pageSize==null){
			pageSize=5;
		}
		List<Article> article1 = articleMapper.findAllArticle(uId,status);
		//分页
		Page articlePage = new Page(currentPage,pageSize,article1.size());
		//查询的分页数据
		List<Article> articlePages = articleMapper.findArticlePage(articlePage, uId,status);
		for (Article page : articlePages) {
			List<Tag> byArticleId = articleMapper.findByArticleId(page.getId());
			page.setTags(byArticleId);
		}
		articlePage.setList(articlePages);
		QueryResult<Page> result = new QueryResult<>();
		ArrayList<Page> list = new ArrayList<>();
		list.add(articlePage);
		result.setTotal(articlePages.size());
		result.setList(list);
		return new QueryResponseResult(CommonCode.SUCCESS,result);
	}

	/**
	 * 添加文章
	 *
	 * @param article
	 * @return
	 */
	public QueryResponseResult addArticle(Article article) {
		if (article != null) {
			article.setCreatTime(new Date());
			int i = articleMapper.insert(article);
			if (i==1){
				List tags = article.getTags();
				for (Object tag : tags) {
					articleMapper.addTag(article.getId(), (Integer) tag);
				}
				QueryResult<Article> result = new QueryResult<>();
				ArrayList<Article> articles = new ArrayList<>();
				articles.add(article);
				result.setList(articles);
				result.setTotal(articles.size());
				return new QueryResponseResult(CommonCode.SUCCESS,result);
			}
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM, null);
	}

	/**
	 * 获取文章
	 * @param ArticleId
	 * @return
	 */
	public QueryResponseResult findById(Integer ArticleId){
		if(ArticleId!=null){
			List<Article> articles = articleMapper.findById(ArticleId);
			QueryResult<Article> result = new QueryResult<>();
			result.setList(articles);
			result.setTotal(articles.size());
			return new QueryResponseResult(CommonCode.SUCCESS,result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}
	/**
	 * 发布草稿
	 * @param ArticleId
	 * @return
	 */
	public QueryResponseResult releaseArticle(Integer ArticleId){
		if(ArticleId!=null){
			int i = articleMapper.releaseArticle(ArticleId);
			QueryResult<Article> result = new QueryResult<>();
			result.setTotal(i);
			return new QueryResponseResult(CommonCode.SUCCESS,result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}
	/**
	 * 删除文章
	 * @param ArticleId
	 * @return
	 */
	public QueryResponseResult removeArticle(Integer ArticleId){
		if(ArticleId!=null){
			int i = articleMapper.deleteArticle(ArticleId);
			int tag = articleMapper.deleteArticleTag(ArticleId);
			QueryResult<Article> result = new QueryResult<>();
			result.setTotal(i);
			return new QueryResponseResult(CommonCode.SUCCESS,result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}
	/**
	 * 更新文章
	 * @param article
	 * @return
	 */
	public QueryResponseResult updateArticle(Article article){
		if(article!=null){
			article.setUpdateTime(new Date());
			int i = articleMapper.updateArticle(article);
			QueryResult<Article> result = new QueryResult<>();
			result.setTotal(i);
			return new QueryResponseResult(CommonCode.SUCCESS,result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}
}

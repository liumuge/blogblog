package com.lhylxl.blogblog.mapper;

import com.lhylxl.blogblog.common.utils.Page;
import com.lhylxl.blogblog.domain.Article;
import com.lhylxl.blogblog.domain.Tag;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/24
 * @time: 9:56
 * @description:
 */
public interface ArticleMapper {

	@Insert("INSERT INTO tb_article(title,content,creatTime,uId,status) VALUES(#{title},#{content},#{creatTime},#{uId},#{status})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	public int insert(Article article);

	@Insert("INSERT Into tb_article_tag(articleId,tagId) values(#{articleId},#{tagId})")
	public int addTag(Integer articleId,Integer tagId);

	@Select("select * from tb_article where uId=#{uId} And status=#{status} LIMIT #{page.start},#{page.pageSize}")
	public List<Article> findArticlePage(Page page,Integer uId,Integer status);

	@Select("select * from tb_article where uId=#{uId} And status=#{status}")
	public List<Article> findAllArticle(Integer uId,Integer status);

	@Select("select * from tb_tag where id=any(SELECT tagId FROM tb_article_tag WHERE articleId=#{ArticleId})")
	public List<Tag> findByArticleId(Integer ArticleId);
}

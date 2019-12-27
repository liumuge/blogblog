package com.lhylxl.blogblog.mapper;

import com.lhylxl.blogblog.common.utils.Page;
import com.lhylxl.blogblog.domain.Article;
import com.lhylxl.blogblog.domain.Tag;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/24
 * @time: 9:56
 * @description:
 */
public interface ArticleMapper {

	@Insert("INSERT INTO tb_article(title,content,contentHtml,creatTime,uId,status) VALUES(#{title},#{content},#{contentHtml},#{creatTime},#{uId},#{status})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	public int insert(Article article);

	@Insert("INSERT Into tb_article_tag(articleId,tagId) values(#{articleId},#{tagId})")
	public int addTag(Integer articleId,Integer tagId);

	@Select("select * from tb_article where uId=#{uId} And status=#{status} ORDER BY creatTime DESC LIMIT #{page.start},#{page.pageSize}")
	public List<Article> findArticlePage(Page page,Integer uId,Integer status);

	@Select("select * from tb_article where uId=#{uId} ORDER BY creatTime DESC LIMIT #{page.start},#{page.pageSize}")
	public List<Article> findArticlePageM(Page page,Integer uId);

	@Select("select * from tb_article where uId=#{uId} And status=#{status}")
	public List<Article> findAllArticle(Integer uId,Integer status);

	@Select("select * from tb_article where uId=#{uId}")
	public List<Article> findAllArticleM(Integer uId);

	@Select("select * from tb_tag where id=any(SELECT tagId FROM tb_article_tag WHERE articleId=#{ArticleId})")
	public List<Tag> findByArticleId(Integer ArticleId);

	@Select("select * from tb_article where id=#{ArticleId}")
	public List<Article> findById(Integer ArticleId);

	@Update("UPDATE tb_article SET  status=1 WHERE id=#{ArticleId}")
	public int releaseArticle(Integer ArticleId);

	@Delete("delete from tb_article WHERE id=#{ArticleId}")
	public int deleteArticle(Integer ArticleId);

	@Update("update tb_article set title=#{title},status=#{status},content=#{content},updateTime=#{updateTime},contentHtml=#{contentHtml} WHERE id=#{id}")
	public int updateArticle(Article article);

	@Delete("delete from tb_article_tag WHERE articleId=#{ArticleId}")
	public int deleteArticleTag(Integer ArticleId);

	@Update("update tb_article_tag set articleId=#{articleId} where tagId=#{tagId}")
	public int updateTag(Integer articleId,Integer tagId);

	@Select("select*from tb_article where content like #{search} and status=#{status} and uId=#{uId} LIMIT #{page.start},#{page.pageSize}")
	public List<Article> search(Page page,Integer uId,String search,Integer status);

	@Select("select*from tb_article where content like #{search}")
	public List<Article> searchTotal(String search);

	@Select("select title from tb_article where id=#{articleId}")
	public String findByArticleIdTitle(Integer articleId);

	@Update("update tb_article set views=#{views} where id=#{ArticleId}")
	public int updateViews(Integer views,Integer ArticleId);

}

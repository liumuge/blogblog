package com.lhylxl.blogblog.mapper;

import com.lhylxl.blogblog.common.utils.Page;
import com.lhylxl.blogblog.domain.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/26
 * @time: 15:00
 * @description:
 */
public interface CommentMapper {

	@Insert("INSERT INTO tb_comment (comment,articleId,uId,status,creatTime) VALUES(#{comment},#{articleId},#{uId},0,#{creatTime})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	public int insert(Comment comment);

	@Select("select *from tb_comment where uId=#{uId}")
	public List<Comment> findAll(Integer uId);

	@Select("select *from tb_comment where uId=#{uId} ORDER BY creatTime DESC LIMIT #{page.start},#{page.pageSize}")
	public List<Comment> findAllComment(Page page,Integer uId);

	@Update("update tb_comment set status=1 where id=#{id}")
	public int updateComment(Integer id);

	@Delete("delete from tb_comment where id=#{id}")
	public int deleteComment(Integer id);

	@Select("select * from tb_comment where articleId=#{articleId} And status=1")
	public List<Comment> findByArticleId(Integer articleId);
}

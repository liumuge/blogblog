package com.lhylxl.blogblog.mapper;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 10:48
 * @description:
 */

import com.lhylxl.blogblog.domain.Tag;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 标签的mapper
 */
public interface TagMapper {

	@Select("select * from tb_tag where uId=#{uId}")
	public List<Tag> findByUId(Integer uId);

	@Delete("DELETE FROM tb_tag WHERE id=#{Id}")
	public int delById(Integer Id);

	@Insert("INSERT INTO tb_tag(tagname,uId) VALUES(#{tagName},#{uId})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	public int Insert(Tag tag);



}

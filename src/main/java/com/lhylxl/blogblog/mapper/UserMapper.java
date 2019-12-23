package com.lhylxl.blogblog.mapper;

import com.lhylxl.blogblog.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/20
 * @time: 15:35
 * @description:
 */

/**
 * 用户的mapper
 */
public interface UserMapper {

	@Select("select *from tb_user where username=#{userName}")
	public List<User> findByName(User user);

	@Insert("INSERT INTO tb_user(username,password,creatTime,token) VALUES(#{userName},#{password},#{creatTime},#{token})")
	@Options(useGeneratedKeys = true,keyProperty = "uId",keyColumn = "uId")
	public int Insert(User user);

	@Select("select *from tb_user where username=#{userName} And password=#{password}")
	public List<User> findUser(User user);

}

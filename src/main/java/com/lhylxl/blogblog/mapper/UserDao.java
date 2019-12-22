package com.lhylxl.blogblog.mapper;

import com.lhylxl.blogblog.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/20
 * @time: 15:35
 * @description:
 */

public interface UserDao {

	@Select("select *from tb_user where username=#{username} And password=#{password}")
	public List<User> findUser(User user);

	@Insert("INSERT INTO tb_user(username,password) VALUES(#{username},#{password})")
	public int Insert(User user);
}

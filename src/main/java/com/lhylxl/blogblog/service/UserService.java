package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.CommonCode;
import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.common.model.response.QueryResult;
import com.lhylxl.blogblog.mapper.UserMapper;
import com.lhylxl.blogblog.domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/20
 * @time: 15:37
 * @description:
 */
@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public QueryResponseResult register(User user) {
		if (user!=null){
			List<User> user1 = userMapper.findByName(user);
			QueryResult<User> result = new QueryResult<>();
			if (user1.size()!=0) {
				return new QueryResponseResult(CommonCode.FALL_USER_REGISTER, result);
			}
			List<User> list = new ArrayList<>();
			list.add(user);
			result.setList(list);
			result.setTotal(list.size());
			if (user.getCreatTime()==null){
				user.setCreatTime(new Date());
			}
			ThreadLocalRandom random = ThreadLocalRandom.current();
			String uuid = new UUID(random.nextInt(), random.nextInt()).toString().replaceAll("-", "");
			user.setToken(uuid);
			userMapper.Insert(user);
			return new QueryResponseResult(CommonCode.SUCCESS, result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM, null);
	}

	/**
	 * 登录
	 * @return
	 */
	public QueryResponseResult login(User user){
		if (user!=null){
			List<User> user1 = userMapper.findUser(user);
			if (user1.size()==1){
				QueryResult<User> result = new QueryResult<>();
				result.setList(user1);
				result.setTotal(user1.size());
				return new QueryResponseResult(CommonCode.SUCCESS,result);
			}
			return new QueryResponseResult(CommonCode.FAIL,null);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}
}

package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.CommonCode;
import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.common.model.response.QueryResult;
import com.lhylxl.blogblog.mapper.UserDao;
import com.lhylxl.blogblog.domain.User;
import java.util.ArrayList;
import java.util.List;
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
	UserDao userDao;

	public QueryResponseResult register(User user) {
		if (user!=null){
			List<User> user1 = userDao.findUser(user);
			QueryResult<User> result = new QueryResult<>();
			if (user1.size()!=0) {
				return new QueryResponseResult(CommonCode.FAIL, result);
			}
			List<User> list = new ArrayList<>();
			list.add(user);
			result.setList(list);
			userDao.Insert(user);
			return new QueryResponseResult(CommonCode.SUCCESS, result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM, null);
	}
}

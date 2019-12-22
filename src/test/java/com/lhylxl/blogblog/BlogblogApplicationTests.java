package com.lhylxl.blogblog;

import com.lhylxl.blogblog.domain.User;
import com.lhylxl.blogblog.mapper.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogblogApplicationTests {

	@Autowired
	UserDao userDao;
	@Test
	void contextLoads() {
		User user = new User();
		user.setPassword("dsfjkl");
		System.out.println(user.toString());
		userDao.Insert(user);
		System.out.println(user);
	}

}

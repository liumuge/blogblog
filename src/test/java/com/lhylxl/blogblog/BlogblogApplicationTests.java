package com.lhylxl.blogblog;

import com.lhylxl.blogblog.domain.User;
import com.lhylxl.blogblog.mapper.UserMapper;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogblogApplicationTests {

	@Autowired
	UserMapper userMapper;
	@Test
	void contextLoads() {
		User user = new User();
		user.setPassword("dsfjkl");
		System.out.println(user.toString());
		userMapper.Insert(user);
		System.out.println(user);
	}

	@Test
	public void testUuid(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
		ThreadLocalRandom random = ThreadLocalRandom.current();
		UUID uuid1 = new UUID(random.nextInt(), random.nextInt());
		System.out.println(uuid1);

	}

}

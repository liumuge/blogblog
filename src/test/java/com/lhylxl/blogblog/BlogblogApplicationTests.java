package com.lhylxl.blogblog;


import com.lhylxl.blogblog.common.utils.Page;
import com.lhylxl.blogblog.domain.Tag;
import com.lhylxl.blogblog.domain.User;
import com.lhylxl.blogblog.mapper.ArticleMapper;
import com.lhylxl.blogblog.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
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

	@Autowired
	ArticleMapper articleMapper;
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

	@Test
	public void testPageHelper(){

	}
	@Test
	public void testPage(){
//		List<User> user11 = userMapper.findUser1();
//		Page userPage = new Page(4,5,user11.size());
//		List<User> user = userMapper.findUser2(userPage);
//		for (User user1 : user) {
//			System.out.println(user1);
//		}
//		userPage.setList(user);
//		System.out.println(userPage);

	}

	@Test
	public void testFindTag(){
		List<Tag> tags = articleMapper.findByArticleId(53);
		for (Tag tag : tags) {
			System.out.println(tag);
		}
	}

	@Test
	public void testQiNiu(){

	}


}

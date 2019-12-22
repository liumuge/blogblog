package com.lhylxl.blogblog.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/20
 * @time: 17:27
 * @description:
 */

@SpringBootTest
public class BadgerDruidApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement prepareStatement = connection
				.prepareStatement("select * from t_city where parent_id='-1'");
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			String cityName = resultSet.getString("name");
			System.out.println(cityName);
		}
	}
}
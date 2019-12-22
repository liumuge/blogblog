package com.lhylxl.blogblog;

import com.lhylxl.blogblog.common.exception.ExceptionCatch;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = "com.lhylxl.blogblog.mapper")
@EnableSwagger2
public class BlogblogApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

	public static void main(String[] args) {
		SpringApplication.run(BlogblogApplication.class, args);
		LOGGER.info("服务启动成功...");
	}

}

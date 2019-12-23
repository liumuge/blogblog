package com.lhylxl.blogblog.controller;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.domain.User;
import com.lhylxl.blogblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/20
 * @time: 15:56
 * @description:
 */
@RestController
@Api(value="user管理接口",description = "user注册，登录")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	@ApiOperation("注册")
	public QueryResponseResult register(@RequestBody User user){
		return userService.register(user);
	}

	@PostMapping("/login")
	@ApiOperation("登录")
	public QueryResponseResult login(@RequestBody User user){
		return userService.login(user);
	}
}

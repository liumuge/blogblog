package com.lhylxl.blogblog.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/20
 * @time: 15:34
 * @description:
 */
@Data
@ToString
public class User {

	private Integer uId;
	private String username;
	private String password;
}

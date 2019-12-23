package com.lhylxl.blogblog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;
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
	private String userName;
	private String password;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	private String avatar;
	private String token;
}

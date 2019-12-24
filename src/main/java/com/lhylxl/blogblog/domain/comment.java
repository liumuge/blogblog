package com.lhylxl.blogblog.domain;

import lombok.Data;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 17:44
 * @description:
 */
@Data
public class comment {

	private Integer id;
	private String comment;
	private Integer articleId;
	private Integer uId;
}

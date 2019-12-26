package com.lhylxl.blogblog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 17:44
 * @description:
 */
@Data
public class Comment {

	private Integer id;
	private String comment;
	private Integer articleId;
	private Integer uId;
	private Integer status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	private String articleTitle;
}

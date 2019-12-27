package com.lhylxl.blogblog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 17:13
 * @description:
 */
@Data
public class Article {

	private Integer id;
	private String title;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatTime;
	private Integer status;
	private String content;
	private String contentHtml;
	private Integer uId;
	private Integer views;
	private List tags;
	private List comments;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}

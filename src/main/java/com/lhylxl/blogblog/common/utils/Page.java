package com.lhylxl.blogblog.common.utils;

import io.swagger.models.auth.In;
import java.util.List;
import lombok.Data;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 18:37
 * @description:
 */
@Data
public class Page<T> {
	//页码
	Integer currentPage;
	//每页显示的记录数
	Integer pageSize;
	//查询开始的索引
	Integer start;
	//总记录数
	Integer totalCount;
	//总页数
	Integer totalPage;
	//结果集合
	List<T> list;


	public Page(int currentPage, int pageSize, int totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		this.start=(currentPage-1)*pageSize;
	}
}

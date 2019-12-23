package com.lhylxl.blogblog.controller;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.domain.Tag;
import com.lhylxl.blogblog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 10:58
 * @description:
 */
@RestController
@Api(value="标签管理接口",description = "增删查改")
@RequestMapping("/tag")
public class TagController {

	@Autowired
	TagService tagService;

	@GetMapping("/getTagAll/{uId}")
	@ApiOperation("根据uid查询所有tag")
	public QueryResponseResult getTagAll(@PathVariable("uId") Integer uId){
		return tagService.getTagAll(uId);
	}

	@DeleteMapping("/delTag/{Id}")
	@ApiOperation("根据id删除tag")
	public QueryResponseResult delTag(@PathVariable("Id") Integer Id){
		return tagService.delTag(Id);
	}

	@PostMapping("/addTag")
	@ApiOperation("添加标签")
	public QueryResponseResult addTag(@RequestBody Tag tag){
		return tagService.addTag(tag);
	}



}

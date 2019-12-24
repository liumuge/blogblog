package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.CommonCode;
import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.common.model.response.QueryResult;
import com.lhylxl.blogblog.domain.Tag;
import com.lhylxl.blogblog.domain.User;
import com.lhylxl.blogblog.mapper.TagMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/23
 * @time: 10:52
 * @description:
 */
@Service
public class TagService {

	@Autowired
	TagMapper tagMapper;

	public QueryResponseResult getTagAll(Integer uId){
		if (uId!=null){
			List<Tag> tags = tagMapper.findByUId(uId);
			QueryResult<Tag> result = new QueryResult<>();
			result.setList(tags);
			result.setTotal(tags.size());
			return new QueryResponseResult(CommonCode.SUCCESS,result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}

	public QueryResponseResult delTag(Integer Id){
		if (Id!=null){
			int i = tagMapper.delById(Id);
			return new QueryResponseResult(CommonCode.SUCCESS,null);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}

	public QueryResponseResult addTag(Tag tag){
		if (tag!=null){
			System.out.println(tag);
			tagMapper.Insert(tag);
			QueryResult<Tag> result = new QueryResult<>();
			List<Tag> list = new ArrayList<>();
			list.add(tag);
			result.setList(list);
			result.setTotal(list.size());
			return new QueryResponseResult(CommonCode.SUCCESS,result);
		}
		return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
	}

}

package com.lhylxl.blogblog.common.exception;

import com.lhylxl.blogblog.common.model.response.ResultCode;

/**
 * @program: xcEduService01
 * @auther: MuGe
 * @date: 2019/12/18
 * @time: 16:03
 * @description:
 */
public class ExceptionCast {

	public static void Cast(ResultCode resultCode){
		throw new CustomException(resultCode);
	}

}

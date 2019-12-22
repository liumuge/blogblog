package com.lhylxl.blogblog.common.exception;

/**
 * @program: xcEduService01
 * @auther: MuGe
 * @date: 2019/12/18
 * @time: 15:41
 * @description:
 */

import com.lhylxl.blogblog.common.model.response.ResultCode;

/**
 * 自定义异常类型
 */
public class CustomException extends RuntimeException{

	//错误代码
	ResultCode resultCode;

	public CustomException(ResultCode resultCode){
		this.resultCode=resultCode;
	};

	public ResultCode getResultCode() {
		return resultCode;
	}
}

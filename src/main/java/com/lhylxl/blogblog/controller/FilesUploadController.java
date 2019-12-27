package com.lhylxl.blogblog.controller;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.service.FilesUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/25
 * @time: 18:40
 * @description:
 */
@RestController
@Api(value = "文件上传", description = "图片")
@RequestMapping("/filesUpload")
public class FilesUploadController {

	@Autowired
	FilesUploadService filesUploadService;

	@PostMapping("/imagesUpload")
	@ApiOperation("图片上传")
	public Map<String, Object> imagesUpload(MultipartFile image) {
		return filesUploadService.imagesUpload(image);
	}



}

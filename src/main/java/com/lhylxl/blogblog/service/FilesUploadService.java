package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import com.lhylxl.blogblog.common.utils.QiniuUtil;
import com.lhylxl.blogblog.domain.User;
import com.lhylxl.blogblog.mapper.UserMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/25
 * @time: 18:41
 * @description:
 */
@Service
public class FilesUploadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FilesUploadService.class);

	@Autowired
	private QiniuUtil qiniuUtil;

	@Autowired
	UserMapper userMapper;

	public Map<String, Object> imagesUpload(MultipartFile file) {
		Map<String, Object> map = new HashMap<>(2);
		try {
			FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
			String originalFilename = file.getOriginalFilename();
			String fileExtend = originalFilename.substring(originalFilename.lastIndexOf("."));
			//默认不指定key的情况下，以文件内容的hash值作为文件名
			String fileKey = UUID.randomUUID().toString().replace("-", "") + fileExtend;
			String fileUrl = qiniuUtil.upload(fileInputStream, fileKey);
			LOGGER.info("上传成功:" + fileUrl);
			map.put("result", true);
			map.put("url", fileUrl);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("图片上传失败:");
		}
		return map;
	};

	/**
	 * 头像上传更新
	 * @param file
	 * @param uId
	 * @return
	 */
	public String avatarUpload(MultipartFile file, Integer uId) {
		try {
			FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
			//默认不指定key的情况下，以文件内容的hash值作为文件名
			String fileKey ;
			//默认不指定key的情况下，以文件内容的hash值作为文件名
			List<User> users = userMapper.findById(uId);
			for (User user : users) {
				fileKey = user.getToken();
			}
			String fileUrl =
					qiniuUtil.upload(fileInputStream, fileKey) + "?t=" + new Date().getTime();
			LOGGER.info("上传成功:" + fileUrl);
			return fileUrl;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("头像上传失败:");
		}
		return null;
	}
}

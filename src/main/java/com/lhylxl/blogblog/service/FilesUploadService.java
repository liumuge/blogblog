package com.lhylxl.blogblog.service;

import com.lhylxl.blogblog.common.model.response.QueryResponseResult;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public Map<String, Object>  imagesUpload(MultipartFile image){
		Map<String, Object> map = new HashMap<>(2);
		//本地使用,上传位置
		String rootPath = null;
		if(System.getProperty("os.name").startsWith("Windows")) {
			rootPath = "D:\\WorkingArea\\WebstormWorkSpace\\myblog\\src\\assets\\images\\";
		}else if (System.getProperty("os.name").startsWith("Linux")){
			rootPath = "D:\\WorkingArea\\WebstormWorkSpace\\myblog\\src\\assets\\images\\";
		}

		//文件的完整名称,如spring.jpeg
		String filename = image.getOriginalFilename();
		//文件后缀,如.jpeg
		String suffix = filename.substring(filename.lastIndexOf("."));

		//创建年月文件夹
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

		//目标文件
		File descFile = new File(rootPath + File.separator + dateDirs + File.separator + filename);

		String newFilename = UUID.randomUUID() + suffix;
		String parentPath = descFile.getParent();
		descFile = new File(parentPath + File.separator + newFilename);

		//判断目标文件所在的目录是否存在
		if (!descFile.getParentFile().exists()) {
			//如果目标文件所在的目录不存在，则创建父目录
			descFile.getParentFile().mkdirs();
		}

		//将内存中的数据写入磁盘
		try {
			image.transferTo(descFile);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("上传失败，cause:{}", e);
			map.put("result", false);
			return map;
		}
		//完整的url
		String fileUrl =""+descFile;
		LOGGER.info("上传成功:"+fileUrl);
		map.put("result", true);
		map.put("url", fileUrl);
		System.out.println(getClass().getClassLoader().getResource("").getPath());
		return map;
	}
}

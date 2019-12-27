package com.lhylxl.blogblog;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: blogblog
 * @auther: MuGe
 * @date: 2019/12/27
 * @time: 14:51
 * @description:
 */
@SpringBootTest
public class QiNiuDemo {

	/**
	 * 将图片上传到七牛云
	 */
	@Test
	public void testUpload() {
		//构造一个带指定 Region 对象的配置类
		//指定文件上传服务器地址
		Configuration cfg = new Configuration(Region.region2());
		//上传管理器
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "2egeABVKkzmgSS_CushuGhfSGOn83sSJ0bJ8i8u_";
		String secretKey = "jHrbPW7xRWy5tubv8tCKFYTo1jHy8KkLKRqMk5E3";
		String bucket = "mugeblog";
		//如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "D:\\图片\\素材\\mmexport1537073822117.jpg";

		//存储到空间的文件名
		String key = "test1";

		//身份认证
		Auth auth = Auth.create(accessKey, secretKey);

//		String upToken = auth.uploadToken(bucket);

		//指定覆盖上传
		String upToken = auth.uploadToken(bucket,key);

		try {
			//上传
			Response response = uploadManager.put(localFilePath, key, upToken);
			//解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				//ignore
			}
		}

	}

	@Test
	public void testUpload02(){
		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(Region.region2());
		//...其他参数参考类注释
		//...生成上传凭证，然后准备上传
		String accessKey = "2egeABVKkzmgSS_CushuGhfSGOn83sSJ0bJ8i8u_";
		String secretKey = "jHrbPW7xRWy5tubv8tCKFYTo1jHy8KkLKRqMk5E3";
		String bucket = "mugeblog";
		//如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "F:\\BaiduNetdiskDownload\\最好的2019✔黑马最新Java✔教程  ！\\黑马Java\\00 讲义+笔记+资料\\1.Java基础\\01.Java基础语法\\01.【前言、入门程序、常量、变量】.zip";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);

		//断点续传:
		String localTempDir = Paths.get(System.getProperty("java.io.tmpdir"), bucket).toString();
		System.out.println(localTempDir);
		try {
			//设置断点续传文件进度保存目录
			FileRecorder fileRecorder = new FileRecorder(localTempDir);
			UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
			try {
				Response response = uploadManager.put(localFilePath, key, upToken);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

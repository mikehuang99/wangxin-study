package com.wangxin.langshu.business;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class Test {

	public static void main(String[] args) {
        Zone zone = new Zone();

		//构造一个带指定 Region 对象的配置类
		Configuration cfg = new Configuration(zone.autoZone());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "UkGYH6LReUhp3J2XgcPHJIQvpflj-cD6B0l1d2-5";
		String secretKey = "dWBTY875pRhWDEw5fcmK6blkbl3QNiF_hE25k8_c";
		String bucket = "test";
		//如果是Windows情况下，格式是 D:\\qiniu\\test.png
		String localFilePath = "/Users/shenge/work/workspaces/workspace-wangxin/图片/第二课.mp4";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = "第二课.mp4";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
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
	}

}

package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;
@Controller
public class PictureController {
	@Value("${http://192.168.25.133/}")
	private String imageserverUrl;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String uploadImage(MultipartFile uploadFile) {
		try {
			String filename = uploadFile.getOriginalFilename();
			String ext = filename.substring(filename.lastIndexOf(".")+1);
			FastDFSClient client = new FastDFSClient("classpath:conf/client.conf");
			String path = client.uploadFile(uploadFile.getBytes(), ext);
			String url = imageserverUrl+path;
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message","错误信息");
			return JsonUtils.objectToJson(result);
		}
	}
}

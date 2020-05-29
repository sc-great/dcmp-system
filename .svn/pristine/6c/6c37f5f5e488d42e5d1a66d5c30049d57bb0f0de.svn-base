package com.great.base.action;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.great.base.entity.Message2Page;
import com.great.resource.StaticData;
import com.great.tool.UUID;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentAction extends BaseAction {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/addPic")
	public Message2Page add(@RequestParam CommonsMultipartFile file) {

		File imgPath = new File(getServletContext().getRealPath(StaticData.IMAGE_UPLOAD_PATH));

		if (!imgPath.exists() && !imgPath.isDirectory()) {
			imgPath.mkdir();
		}

		String originalFilename = file.getOriginalFilename();
		String imageType = originalFilename.substring(originalFilename.lastIndexOf("."));
		String fileName = UUID.randomUUID() + imageType;
		try {
			file.transferTo(new File(imgPath, fileName));
		} catch (IOException e) {
			return new Message2Page(false, "301", "服务器文件写入失败");
		}
		// String msg = "上传图片：" + originalFilename;
		// logger.info(msg);
		// saveLogB(msg, StaticData.LOG_TYPE_DO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url", getServletContext().getContextPath() + StaticData.IMAGE_UPLOAD_PATH + fileName);
		Message2Page message2Page = new Message2Page(true, "200", null);
		message2Page.setJob(jsonObject);
		return message2Page;
	}
}

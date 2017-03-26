package com.shana.laboratory.sheetimage.action;

import java.util.Map;

import javax.persistence.Id;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/laboratorysheetimage")
public class LaboratorySheetImageController {
	
	/**
	 * 1、检查image 内容是否为化验单
	 * 2、保存image
	 * 3、调用百度ocr api获取图片文字
	 * 4、返回image id和图片文字
	 * @param image
	 * @return Map with image id
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Map<String,Object> uploadSheetImage(MultipartFile image)
	{
		return null;
	}

}

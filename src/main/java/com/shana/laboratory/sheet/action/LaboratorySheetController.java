package com.shana.laboratory.sheet.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shana.laboratory.sheet.dao.LaboratorySheetRepository;
import com.shana.laboratory.sheet.pojo.LaboratorySheet;

@RestController
@RequestMapping("/user/{userId}/laboratorysheet")
public class LaboratorySheetController {
	
	@Autowired
	LaboratorySheetRepository laboratorySheetRepository;
	
	/**
	 * 1、解析text 获取name
	 * 2、解析text 获取化验日期
	 * 3、解析text 为 List<LaboratorySheetItem>
	 * 4、保存LoboratorySheet对象
	 * @param userId
	 * @param sheet
	 * @return Map with sheet id,or failure message
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Map<String,Object> create(@PathVariable String userId,@RequestBody LaboratorySheet sheet)
	{
		
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Map<String,Object> edit(@PathVariable String userId,@RequestBody LaboratorySheet sheet)
	{
		return null;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Map<String,Object> get(@PathVariable String userId,@PathVariable String id)
	{
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String,Object> gets(@PathVariable String userId,Integer pageIndex,Integer pageSize)
	{
		return null;
	}


}

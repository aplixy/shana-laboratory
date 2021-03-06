package com.shana.laboratory.index.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shana.exception.ShanaInputParameterIsNullException;
import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;
import com.shana.laboratory.index.service.LaboratoryIndexDraftService;
import com.shana.resultset.ResultSet;

@RestController
@RequestMapping("/laboratoryindexdrafts")
public class LaboratoryIndexDraftController {
	
	@Autowired
	private LaboratoryIndexDraftService LaboratoryIndexDraftService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String,Object> create(@RequestBody LaboratoryIndexDraft index) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraft laboratloryIndex = LaboratoryIndexDraftService.create(index);
		result.put("id", laboratloryIndex.getId());
		return result;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Map<String,Object> update(@RequestBody LaboratoryIndexDraft index) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraftService.update(index);
		return result;
	}

	@RequestMapping(value="/{indexDraftId}",method = RequestMethod.DELETE)
	public Map<String,Object> delete(@PathVariable String indexDraftId) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraftService.delete(indexDraftId);
		return result;

	}

	@RequestMapping(value="/{indexDraftId}",method = RequestMethod.GET)
	public Map<String,Object> get(@PathVariable String indexDraftId) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraft data = LaboratoryIndexDraftService.get(indexDraftId);
		result.put("data", data);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Map<String,Object> gets(String type, String code,String cnName,Integer pageIndex, Integer pageSize) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		ResultSet<LaboratoryIndexDraft> resultSet = LaboratoryIndexDraftService.gets(type, code, cnName, pageIndex, pageSize);
		result.put("data", resultSet.getData());
		result.put("totalPage", resultSet.getTotalPage());
		result.put("totalRow",  resultSet.getTotalRow());	
		return result;
	}

	@RequestMapping(value="/{indexDraftId}/status",method = RequestMethod.POST)
	public Map<String,Object> updateStatus(@PathVariable String indexDraftId,@RequestBody LaboratoryIndexDraft indexDraft) throws Exception {
		if (null == indexDraft)
			throw new ShanaInputParameterIsNullException("");
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraftService.updateStatus(indexDraftId, indexDraft.getStatus());
		return result;
	}
}

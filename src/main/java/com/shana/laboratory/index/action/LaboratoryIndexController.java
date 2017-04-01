package com.shana.laboratory.index.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shana.laboratory.index.pojo.LaboratoryIndex;
import com.shana.laboratory.index.service.LaboratoryIndexService;

@RestController
@RequestMapping("/laboratoryindexs")
public class LaboratoryIndexController {
	
	@Autowired
	private LaboratoryIndexService laboratoryIndexService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String,Object> create(@RequestBody LaboratoryIndex index) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndex laboratloryIndex = laboratoryIndexService.create(index);
		result.put("id", laboratloryIndex.getId());
		return result;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Map<String,Object> update(@RequestBody LaboratoryIndex index) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		laboratoryIndexService.update(index);
		return result;
	}

	@RequestMapping(value="/{indexId}",method = RequestMethod.DELETE)
	public Map<String,Object> delete(@PathVariable String indexId) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		laboratoryIndexService.delete(indexId);
		return result;

	}

	@RequestMapping(value="/{indexId}",method = RequestMethod.GET)
	public Map<String,Object> get(@PathVariable String indexId) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndex data = laboratoryIndexService.get(indexId);
		result.put("data", data);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Map<String,Object> gets(String search, Integer pageIndex, Integer pageSize) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		List<LaboratoryIndex> data = laboratoryIndexService.gets(search, pageIndex, pageSize);
		result.put("data", data);
		return result;
	}

}

package com.shana.laboratory.index.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraftApproval;
import com.shana.laboratory.index.service.LaboratoryIndexDraftApprovalService;

@RestController
@RequestMapping("/laboratoryindexdraftapprovals")
public class LaboratoryIndexDraftApprovalController {
	
	@Autowired
	private LaboratoryIndexDraftApprovalService laboratoryIndexDraftApprovalService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String,Object> create(@RequestBody LaboratoryIndexDraftApproval index) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraftApproval laboratloryIndexDraftApproval = laboratoryIndexDraftApprovalService.create(index);
		result.put("id", laboratloryIndexDraftApproval.getId());
		return result;
	}

	@RequestMapping(value="/{indexDraftId}",method = RequestMethod.GET)
	public Map<String,Object> get(@PathVariable String indexDraftId) throws Exception {
		Map<String,Object> result=new HashMap<String,Object>();
		LaboratoryIndexDraftApproval data = laboratoryIndexDraftApprovalService.getByeIndexDraftId(indexDraftId);
		result.put("data", data);
		return result;
	}

}

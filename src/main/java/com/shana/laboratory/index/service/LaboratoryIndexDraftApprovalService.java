package com.shana.laboratory.index.service;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraftApproval;

public interface LaboratoryIndexDraftApprovalService {
	
	public LaboratoryIndexDraftApproval create(LaboratoryIndexDraftApproval index) throws Exception;
	public LaboratoryIndexDraftApproval getByeIndexDraftId(String indexDraftId) throws Exception;

}

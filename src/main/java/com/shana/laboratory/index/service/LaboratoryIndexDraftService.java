package com.shana.laboratory.index.service;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;
import com.shana.resultset.ResultSet;

public interface LaboratoryIndexDraftService {
	
	public LaboratoryIndexDraft create(LaboratoryIndexDraft indexDraft) throws Exception;
	public LaboratoryIndexDraft update(LaboratoryIndexDraft indexDraft) throws Exception;
	public void delete(String indexDraftId) throws Exception;
	public LaboratoryIndexDraft get(String indexDraftId) throws Exception;
	public ResultSet<LaboratoryIndexDraft> gets(String type, String code,String cnName,Integer pageIndex,Integer pageSize) throws Exception;
	public LaboratoryIndexDraft updateStatus(String id,String status) throws Exception;
}

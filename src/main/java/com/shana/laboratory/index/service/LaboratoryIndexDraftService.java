package com.shana.laboratory.index.service;

import java.util.List;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;

public interface LaboratoryIndexDraftService {
	
	public LaboratoryIndexDraft create(LaboratoryIndexDraft indexDraft) throws Exception;
	public LaboratoryIndexDraft update(LaboratoryIndexDraft indexDraft) throws Exception;
	public void delete(String indexDraftId) throws Exception;
	public LaboratoryIndexDraft get(String indexDraftId) throws Exception;
	public List<LaboratoryIndexDraft> gets(String type, String code,String cnName,Integer pageIndex,Integer pageSize) throws Exception;
	public LaboratoryIndexDraft patch(LaboratoryIndexDraft indexDraft) throws Exception;
}

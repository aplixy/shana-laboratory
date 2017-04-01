package com.shana.laboratory.index.service;

import java.util.List;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;

public interface LaboratoryIndexDraftService {
	
	public LaboratoryIndexDraft create(LaboratoryIndexDraft index) throws Exception;
	public LaboratoryIndexDraft update(LaboratoryIndexDraft index) throws Exception;
	public void delete(String indexId) throws Exception;
	public LaboratoryIndexDraft get(String indexId) throws Exception;
	public List<LaboratoryIndexDraft> gets(String type, String code,String cnName,Integer pageIndex,Integer pageSize) throws Exception;

}

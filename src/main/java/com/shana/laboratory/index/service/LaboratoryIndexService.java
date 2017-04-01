package com.shana.laboratory.index.service;

import java.util.List;

import com.shana.laboratory.index.pojo.LaboratoryIndex;

public interface LaboratoryIndexService {
	
	public LaboratoryIndex create(LaboratoryIndex index) throws Exception;
	public LaboratoryIndex update(LaboratoryIndex index) throws Exception;
	public void delete(String indexId) throws Exception;
	public LaboratoryIndex get(String indexId) throws Exception;
	public List<LaboratoryIndex> gets(String search,Integer pageIndex,Integer pageSize) throws Exception;

}

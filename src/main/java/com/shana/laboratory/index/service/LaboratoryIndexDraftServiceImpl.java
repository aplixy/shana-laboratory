package com.shana.laboratory.index.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shana.laboratory.index.dao.LaboratoryIndexDraftRepository;
import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;
import com.shana.utils.IdUtils;

@Service
public class LaboratoryIndexDraftServiceImpl implements LaboratoryIndexDraftService {

	@Autowired
	LaboratoryIndexDraftRepository LaboratoryIndexDraftRepository;
	
	@Autowired
	IdUtils idUtils;
	
	@Override
	public LaboratoryIndexDraft create(LaboratoryIndexDraft indexDraft) throws Exception {
		
		validateWhenCreate(indexDraft);		
		String id=idUtils.genarateId(LaboratoryIndexDraft.class.getName());
		indexDraft.setId(id);
		return LaboratoryIndexDraftRepository.save(indexDraft);
	}

	
	@Override
	public LaboratoryIndexDraft update(LaboratoryIndexDraft indexDraft) throws Exception {
		
		validateWhenUpdate(indexDraft);		
		return LaboratoryIndexDraftRepository.save(indexDraft);
	}

	@Override
	public void delete(String indexDraftId) throws Exception {
		if(StringUtils.isEmpty(indexDraftId))
		{
			throw new Exception("The input parameter 'indexId' is null.");
		}
		if(null==LaboratoryIndexDraftRepository.findOne(indexDraftId))
		{
			throw new Exception("The laboratory index object is null,witch id is "+indexDraftId+".");
		}
		LaboratoryIndexDraftRepository.delete(indexDraftId);
		

	}

	@Override
	public LaboratoryIndexDraft get(String indexDraftId) throws Exception {
		if(StringUtils.isEmpty(indexDraftId))
		{
			throw new Exception("The input parameter 'indexId' is null.");
		}
		//return LaboratoryIndexDraftRepository.findOne(indexDraftId);
		LaboratoryIndexDraft indexDraft=new LaboratoryIndexDraft();
		indexDraft.setCode("lldcode");
		indexDraft.setCnName("中国年我");
		indexDraft.setEnName("en nae");
		return indexDraft;
		
	}

	@Override
	public List<LaboratoryIndexDraft> gets(String type, String code,String cnName,Integer pageIndex, Integer pageSize) throws Exception {
		Iterable<LaboratoryIndexDraft> laboratoryIndexDrafts = LaboratoryIndexDraftRepository.findAll();
		Iterator<LaboratoryIndexDraft> iterator = laboratoryIndexDrafts.iterator();
		List<LaboratoryIndexDraft> result=new ArrayList<LaboratoryIndexDraft>();
		while(iterator.hasNext())
		{
			result.add(iterator.next());
		}
		return result;
	}
	
	private void validateWhenCreate(LaboratoryIndexDraft indexDraft) throws Exception {
		validateInputParameterIsNotNull(indexDraft);
		
		String code=indexDraft.getCode();
		List<LaboratoryIndexDraft> laboratoryIndexDrafts = LaboratoryIndexDraftRepository.findByCode(code);
		if(null!=laboratoryIndexDrafts&&!laboratoryIndexDrafts.isEmpty())
			if(StringUtils.isEmpty(indexDraft.getUnit()))
				throw new Exception("The laboratory index draft object is duplicate, which code is "+code+".");
			
	}

	private void validateWhenUpdate(LaboratoryIndexDraft indexDraft) throws Exception {
		validateInputParameterIsNotNull(indexDraft);
		if(StringUtils.isEmpty(indexDraft.getId()))
			throw new Exception("The input parameter id is null.");
		
	}
	
	private void validateInputParameterIsNotNull(LaboratoryIndexDraft indexDraft) throws Exception {
		if(null==indexDraft)
			throw new Exception("The input parameter is null.");
		if(StringUtils.isEmpty(indexDraft.getCode()))
			throw new Exception("The input parameter code is null.");
		
		if(StringUtils.isEmpty(indexDraft.getType()))
			throw new Exception("The input parameter type is null.");
		if(StringUtils.isEmpty(indexDraft.getCnName()))
			throw new Exception("The input parameter cnName is null.");
		if(StringUtils.isEmpty(indexDraft.getEnName()))
			throw new Exception("The input parameter enName is null.");
		
		if(StringUtils.isEmpty(indexDraft.getUnit()))
			throw new Exception("The input parameter unit is null.");
			
	}


}

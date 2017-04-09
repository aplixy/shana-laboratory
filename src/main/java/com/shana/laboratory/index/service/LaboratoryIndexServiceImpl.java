package com.shana.laboratory.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shana.exception.ShanaException;
import com.shana.exception.ShanaInputParameterIsNullException;
import com.shana.laboratory.index.dao.LaboratoryIndexRepository;
import com.shana.laboratory.index.pojo.LaboratoryIndex;
import com.shana.utils.IdUtils;

@Service
public class LaboratoryIndexServiceImpl implements LaboratoryIndexService {

	@Autowired
	LaboratoryIndexRepository laboratoryIndexRepository;
	
	@Autowired
	IdUtils idUtils;
	
	@Override
	public LaboratoryIndex create(LaboratoryIndex index) throws Exception {
		
		validateWhenCreate(index);		
		String id=idUtils.genarateId(LaboratoryIndex.class.getName());
		index.setId(id);
		return laboratoryIndexRepository.save(index);
	}

	
	@Override
	public LaboratoryIndex update(LaboratoryIndex index) throws Exception {
		
		validateWhenUpdate(index);		
		return laboratoryIndexRepository.save(index);
	}

	@Override
	public void delete(String indexId) throws Exception {
		if(StringUtils.isEmpty(indexId))
		{
			throw new ShanaInputParameterIsNullException("id");
		}
		if(null==laboratoryIndexRepository.findOne(indexId))
		{
			throw new ShanaException("object_is_null","The laboratory index object is null,witch id is "+indexId+".",null);
		}
		laboratoryIndexRepository.delete(indexId);
		

	}

	@Override
	public LaboratoryIndex get(String indexId) throws Exception {
		if(StringUtils.isEmpty(indexId))
		{
			throw new ShanaInputParameterIsNullException("id");
		}
		return laboratoryIndexRepository.findOne(indexId);
	}

	@Override
	public List<LaboratoryIndex> gets(String search, Integer pageIndex, Integer pageSize) throws Exception {
		
		return null;
	}
	
	private void validateWhenCreate(LaboratoryIndex index) throws Exception {
		validateInputParameterIsNotNull(index);
		
		String code=index.getCode();
		LaboratoryIndex laboratoryIndex = laboratoryIndexRepository.findByCode(code);
		if(null!=laboratoryIndex)
			if(StringUtils.isEmpty(index.getUnit()))
				throw new ShanaException("object_is_duplicate","The laboratory index object is duplicate, which code is "+code+".",null);
			
	}

	private void validateWhenUpdate(LaboratoryIndex index) throws Exception {
		validateInputParameterIsNotNull(index);
		if(StringUtils.isEmpty(index.getId()))
			throw new ShanaInputParameterIsNullException("id");
		
	}
	
	private void validateInputParameterIsNotNull(LaboratoryIndex index) throws Exception {
		if(null==index)
			throw new ShanaInputParameterIsNullException("");
		if(StringUtils.isEmpty(index.getCode()))
			throw new ShanaInputParameterIsNullException("code");
		
		if(StringUtils.isEmpty(index.getType()))
			throw new ShanaInputParameterIsNullException("type");
		
		if(StringUtils.isEmpty(index.getCnName()))
			throw new ShanaInputParameterIsNullException("cnName");
		
		if(StringUtils.isEmpty(index.getEnName()))
			throw new ShanaInputParameterIsNullException("enName");
		
		if(StringUtils.isEmpty(index.getUnit()))
			throw new ShanaInputParameterIsNullException("unit");
			
	}


}

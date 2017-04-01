package com.shana.laboratory.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
			throw new Exception("The input parameter 'indexId' is null.");
		}
		if(null==laboratoryIndexRepository.findOne(indexId))
		{
			throw new Exception("The laboratory index object is null,witch id is "+indexId+".");
		}
		laboratoryIndexRepository.delete(indexId);
		

	}

	@Override
	public LaboratoryIndex get(String indexId) throws Exception {
		if(StringUtils.isEmpty(indexId))
		{
			throw new Exception("The input parameter 'indexId' is null.");
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
				throw new Exception("The laboratory index object is duplicate, which code is "+code+".");
			
	}

	private void validateWhenUpdate(LaboratoryIndex index) throws Exception {
		validateInputParameterIsNotNull(index);
		if(StringUtils.isEmpty(index.getId()))
			throw new Exception("The input parameter id is null.");
		
	}
	
	private void validateInputParameterIsNotNull(LaboratoryIndex index) throws Exception {
		if(null==index)
			throw new Exception("The input parameter is null.");
		if(StringUtils.isEmpty(index.getCode()))
			throw new Exception("The input parameter code is null.");
		
		if(StringUtils.isEmpty(index.getType()))
			throw new Exception("The input parameter type is null.");
		if(StringUtils.isEmpty(index.getCnName()))
			throw new Exception("The input parameter cnName is null.");
		if(StringUtils.isEmpty(index.getEnName()))
			throw new Exception("The input parameter enName is null.");
		
		if(StringUtils.isEmpty(index.getUnit()))
			throw new Exception("The input parameter unit is null.");
			
	}


}

package com.shana.laboratory.index.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shana.exception.ShanaException;
import com.shana.exception.ShanaInputParameterIsNullException;
import com.shana.laboratory.index.dao.LaboratoryIndexDraftRepository;
import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;
import com.shana.resultset.ResultSet;
import com.shana.utils.IdUtils;

@Service
public class LaboratoryIndexDraftServiceImpl implements LaboratoryIndexDraftService {

	@Autowired
	LaboratoryIndexDraftRepository laboratoryIndexDraftRepository;

	@Autowired
	IdUtils idUtils;

	@Override
	public LaboratoryIndexDraft create(LaboratoryIndexDraft indexDraft) throws Exception {

		validateWhenCreate(indexDraft);
		String id = idUtils.genarateId(LaboratoryIndexDraft.class.getName());
		indexDraft.setId(id);
		indexDraft.setStatus("CREATING");
		return laboratoryIndexDraftRepository.save(indexDraft);
	}

	@Override
	public LaboratoryIndexDraft update(LaboratoryIndexDraft indexDraft) throws Exception {

		validateWhenUpdate(indexDraft);
		indexDraft.setStatus("CREATING");
		return laboratoryIndexDraftRepository.save(indexDraft);
	}

	@Override
	public void delete(String indexDraftId) throws Exception {
		if (StringUtils.isEmpty(indexDraftId)) {
			throw new ShanaInputParameterIsNullException("id");
		}
		if (null == laboratoryIndexDraftRepository.findOne(indexDraftId)) {
			throw new ShanaException("object_is_null","The laboratory index draft object is null,witch id is " + indexDraftId + ".",null);
		}
		laboratoryIndexDraftRepository.delete(indexDraftId);

	}

	@Override
	public LaboratoryIndexDraft get(String indexDraftId) throws Exception {
		if (StringUtils.isEmpty(indexDraftId)) {
			throw new ShanaInputParameterIsNullException("id");
		}
	    return laboratoryIndexDraftRepository.findOne(indexDraftId);

	}

	@Override
	public ResultSet<LaboratoryIndexDraft> gets(String type, String code, String cnName, Integer pageIndex, Integer pageSize)
			throws Exception {
		if (null == pageIndex || 1 > pageIndex)
			pageIndex = 1;
		if (null == pageSize || 1 > pageSize)
			pageSize = 10;
		Sort sort = new Sort(Direction.DESC, "code");
		Page<LaboratoryIndexDraft> laboratoryIndexDrafts = laboratoryIndexDraftRepository
				.findAll(getWhere(type, code, cnName), new PageRequest(pageIndex - 1, pageSize, sort));
		ResultSet<LaboratoryIndexDraft> resultSet=new ResultSet<LaboratoryIndexDraft>();
		resultSet.setData(laboratoryIndexDrafts.getContent());
		resultSet.setTotalPage(laboratoryIndexDrafts.getTotalPages());
		resultSet.setTotalRow(laboratoryIndexDrafts.getTotalElements());
		return resultSet;
	}
	
	@Override
	public LaboratoryIndexDraft updateStatus(String id,String status) throws Exception {
	
		if (StringUtils.isEmpty(id))
			throw new ShanaInputParameterIsNullException("id");
		
		if (StringUtils.isEmpty(status))
			throw new ShanaInputParameterIsNullException("status");
		
		if ("APPROVAL_SUCCESS".equals(status)||"APPROVAL_REJECT".equals(status))
			throw new ShanaException("input_is_invalid","The status value can not be 'APPROVAL_SUCCESS' or 'APPROVAL_REJECT'.",null);
		
		LaboratoryIndexDraft oldIndexDraft = laboratoryIndexDraftRepository.findOne(id);
		if(null!=oldIndexDraft){
			if(!StringUtils.isEmpty(status)&&!"APPROVAL_SUCCESS".equals(oldIndexDraft.getStatus()))
			{
				oldIndexDraft.setStatus(status);
			}
		}
		
		
		return laboratoryIndexDraftRepository.save(oldIndexDraft);
	}

	private Specification<LaboratoryIndexDraft> getWhere(final String type, final String code, final String cnName) {

		return new Specification<LaboratoryIndexDraft>() {

			@Override
			public Predicate toPredicate(Root<LaboratoryIndexDraft> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(type)) {
					predicate.add(cb.equal(root.get("type").as(String.class), type));
				}
				if (!StringUtils.isEmpty(code)) {
					predicate.add(cb.like(root.get("code").as(String.class), "%"+code+"%"));
				}
				if (!StringUtils.isEmpty(cnName)) {
					predicate.add(cb.like(root.get("cnName").as(String.class), "%"+cnName+"%"));
				}
				Predicate[] pre=new Predicate[predicate.size()];
				
				return query.where(predicate.toArray(pre)).getRestriction();
			}

		};
	}

	private void validateWhenCreate(LaboratoryIndexDraft indexDraft) throws Exception {
		validateInputParameterIsNotNull(indexDraft);

		String code = indexDraft.getCode();
		List<LaboratoryIndexDraft> laboratoryIndexDrafts = laboratoryIndexDraftRepository.findByCode(code);
		if (null != laboratoryIndexDrafts && !laboratoryIndexDrafts.isEmpty())
			if (StringUtils.isEmpty(indexDraft.getUnit()))
				throw new ShanaException("object_is_duplicate","The laboratory index draft object is duplicate, which code is " + code + ".",null);

	}

	private void validateWhenUpdate(LaboratoryIndexDraft indexDraft) throws Exception {
		validateInputParameterIsNotNull(indexDraft);
		String id=indexDraft.getId();
		if (StringUtils.isEmpty(id))
			throw new ShanaInputParameterIsNullException("id");
		
		LaboratoryIndexDraft oldIndexDraft = laboratoryIndexDraftRepository.findOne(id);
		if(null!=oldIndexDraft&&!"CREATING".equals(oldIndexDraft.getStatus())&&!StringUtils.isEmpty(oldIndexDraft.getStatus()))
		{
			throw new ShanaException("forbidden","The laboratory draft object can not be update,when its status is not 'CREATING'.",null);
		}
		

	}

	private void validateInputParameterIsNotNull(LaboratoryIndexDraft indexDraft) throws Exception {
		if (null == indexDraft)
			throw new ShanaInputParameterIsNullException("");
		if (StringUtils.isEmpty(indexDraft.getCode()))
			throw new ShanaInputParameterIsNullException("code");

		if (StringUtils.isEmpty(indexDraft.getType()))
			throw new ShanaInputParameterIsNullException("type");
		if (StringUtils.isEmpty(indexDraft.getCnName()))
			throw new ShanaInputParameterIsNullException("cnName");
		if (StringUtils.isEmpty(indexDraft.getEnName()))
			throw new ShanaInputParameterIsNullException("enName");

		if (StringUtils.isEmpty(indexDraft.getUnit()))
			throw new ShanaInputParameterIsNullException("unit");

	}

	

}

package com.shana.laboratory.index.service;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.shana.laboratory.index.dao.LaboratoryIndexDraftRepository;
import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;
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
		return laboratoryIndexDraftRepository.save(indexDraft);
	}

	@Override
	public LaboratoryIndexDraft update(LaboratoryIndexDraft indexDraft) throws Exception {

		validateWhenUpdate(indexDraft);
		return laboratoryIndexDraftRepository.save(indexDraft);
	}

	@Override
	public void delete(String indexDraftId) throws Exception {
		if (StringUtils.isEmpty(indexDraftId)) {
			throw new Exception("The input parameter 'indexId' is null.");
		}
		if (null == laboratoryIndexDraftRepository.findOne(indexDraftId)) {
			throw new Exception("The laboratory index object is null,witch id is " + indexDraftId + ".");
		}
		laboratoryIndexDraftRepository.delete(indexDraftId);

	}

	@Override
	public LaboratoryIndexDraft get(String indexDraftId) throws Exception {
		if (StringUtils.isEmpty(indexDraftId)) {
			throw new Exception("The input parameter 'indexId' is null.");
		}
		// return LaboratoryIndexDraftRepository.findOne(indexDraftId);
		LaboratoryIndexDraft indexDraft = new LaboratoryIndexDraft();
		indexDraft.setCode("lldcode");
		indexDraft.setCnName("中国年我");
		indexDraft.setEnName("en nae");
		return indexDraft;

	}

	@Override
	public List<LaboratoryIndexDraft> gets(String type, String code, String cnName, Integer pageIndex, Integer pageSize)
			throws Exception {
		if (null == pageIndex || 1 > pageIndex)
			pageIndex = 1;
		if (null == pageSize || 1 > pageSize)
			pageSize = 10;
		Sort sort = new Sort(Direction.DESC, "code");
		Page<LaboratoryIndexDraft> laboratoryIndexDrafts = laboratoryIndexDraftRepository
				.findAll(getWhere(type, code, cnName), new PageRequest(pageIndex - 1, pageSize, sort));
		return laboratoryIndexDrafts.getContent();
	}

	private Specification<LaboratoryIndexDraft> getWhere(final String type, final String code, final String cnName) {

		return new Specification<LaboratoryIndexDraft>() {

			@Override
			public Predicate toPredicate(Root<LaboratoryIndexDraft> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if (StringUtils.isEmpty(type)) {
					predicate.add(cb.equal(root.get("type").as(String.class), type));
				}
				if (StringUtils.isEmpty(code)) {
					predicate.add(cb.like(root.get("code").as(String.class), "%"+code+"%"));
				}
				if (StringUtils.isEmpty(cnName)) {
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
				throw new Exception("The laboratory index draft object is duplicate, which code is " + code + ".");

	}

	private void validateWhenUpdate(LaboratoryIndexDraft indexDraft) throws Exception {
		validateInputParameterIsNotNull(indexDraft);
		if (StringUtils.isEmpty(indexDraft.getId()))
			throw new Exception("The input parameter id is null.");

	}

	private void validateInputParameterIsNotNull(LaboratoryIndexDraft indexDraft) throws Exception {
		if (null == indexDraft)
			throw new Exception("The input parameter is null.");
		if (StringUtils.isEmpty(indexDraft.getCode()))
			throw new Exception("The input parameter code is null.");

		if (StringUtils.isEmpty(indexDraft.getType()))
			throw new Exception("The input parameter type is null.");
		if (StringUtils.isEmpty(indexDraft.getCnName()))
			throw new Exception("The input parameter cnName is null.");
		if (StringUtils.isEmpty(indexDraft.getEnName()))
			throw new Exception("The input parameter enName is null.");

		if (StringUtils.isEmpty(indexDraft.getUnit()))
			throw new Exception("The input parameter unit is null.");

	}

}

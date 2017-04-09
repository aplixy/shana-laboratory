package com.shana.laboratory.index.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shana.exception.ShanaException;
import com.shana.exception.ShanaInputParameterIsNullException;
import com.shana.laboratory.index.dao.LaboratoryIndexDraftApprovalRepository;
import com.shana.laboratory.index.dao.LaboratoryIndexDraftRepository;
import com.shana.laboratory.index.dao.LaboratoryIndexRepository;
import com.shana.laboratory.index.pojo.LaboratoryIndex;
import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;
import com.shana.laboratory.index.pojo.LaboratoryIndexDraftApproval;
import com.shana.laboratory.index.utils.LaboratoryIndexContantUtils;
import com.shana.utils.IdUtils;

@Service
public class LaboratoryIndexDraftApprovalServiceImpl implements LaboratoryIndexDraftApprovalService {

	@Autowired
	private LaboratoryIndexDraftApprovalRepository laboratoryIndexDraftApprovalRepository;
	
	@Autowired
	private LaboratoryIndexDraftRepository laboratoryIndexDraftRepository;
	
	@Autowired
	private LaboratoryIndexRepository laboratoryIndexRepository;
	
	@Autowired
	IdUtils idUtils;
	
	@Autowired
	LaboratoryIndexContantUtils laboratoryIndexContantUtils;
	
	@Override
	public LaboratoryIndexDraftApproval create(LaboratoryIndexDraftApproval indexDraftApproval) throws Exception {
		validateInputParameterIsNotNull(indexDraftApproval);
		String indexDraftId=indexDraftApproval.getIndexDraftId();
		LaboratoryIndexDraft laboratoryIndexDraft = getAndValidateLaboratoryIndexDraft(indexDraftId);
		String id = idUtils.genarateId(LaboratoryIndexDraftApproval.class.getName());
		indexDraftApproval.setId(id);
		String result=indexDraftApproval.getApprovalResult();
		if("OK".equals(result))
		{
			createLaboratoryIndexByDraft(laboratoryIndexDraft);
			laboratoryIndexDraft.setStatus(laboratoryIndexContantUtils.LABORATORY_INDEX_DRAFT_STATUS_APPROVAL_SUCCESS);
		}
		else
		{
			
			laboratoryIndexDraft.setStatus(laboratoryIndexContantUtils.LABORATORY_INDEX_DRAFT_STATUS_APPROVAL_REJECT);
		
		}
		laboratoryIndexDraftRepository.save(laboratoryIndexDraft);
		return laboratoryIndexDraftApprovalRepository.save(indexDraftApproval);
	}

	private void createLaboratoryIndexByDraft(LaboratoryIndexDraft laboratoryIndexDraft) {
		LaboratoryIndex laboratoryIndex=new LaboratoryIndex();
		BeanUtils.copyProperties(laboratoryIndexDraft, laboratoryIndex);
		laboratoryIndexRepository.save(laboratoryIndex);
		syncLaboratoryIndexToElasticsearch(laboratoryIndex);
		
	}

	private void syncLaboratoryIndexToElasticsearch(LaboratoryIndex laboratoryIndex) {
		// TODO Auto-generated method stub
		
	}

	private LaboratoryIndexDraft getAndValidateLaboratoryIndexDraft(String indexDraftId) throws Exception {
		LaboratoryIndexDraft laboratoryIndexDraft = laboratoryIndexDraftRepository.findOne(indexDraftId);
		if(null==laboratoryIndexDraft)
			throw new ShanaException("object_is_null","The laboratory index draft approval object is null,witch id is " + indexDraftId + ".",null);
		return laboratoryIndexDraft;
	}

	@Override
	public LaboratoryIndexDraftApproval getByeIndexDraftId(String indexDraftId) throws Exception {
		if (StringUtils.isEmpty(indexDraftId)) {
			throw new ShanaInputParameterIsNullException("indexDraftId");
		}
		List<LaboratoryIndexDraftApproval> laboratoryIndexDraftApprovals = laboratoryIndexDraftApprovalRepository.findByIndexDraftId(indexDraftId);
	    return laboratoryIndexDraftApprovals.isEmpty()?null:laboratoryIndexDraftApprovals.get(0);
	}

	private void validateInputParameterIsNotNull(LaboratoryIndexDraftApproval indexDraftApproval) throws Exception {
		if (null == indexDraftApproval)
			throw new ShanaInputParameterIsNullException("");
		if (StringUtils.isEmpty(indexDraftApproval.getIndexDraftId()))
			throw new ShanaInputParameterIsNullException("indexDraftId");

		if (StringUtils.isEmpty(indexDraftApproval.getApprover()))
			throw new ShanaInputParameterIsNullException("approver");
		
		if (StringUtils.isEmpty(indexDraftApproval.getApprovalResult()))
			throw new ShanaInputParameterIsNullException("approvalResult");
		
		if (StringUtils.isEmpty(indexDraftApproval.getApprovalOpinion()))
			throw new ShanaInputParameterIsNullException("approvalOpinion");

	}
}

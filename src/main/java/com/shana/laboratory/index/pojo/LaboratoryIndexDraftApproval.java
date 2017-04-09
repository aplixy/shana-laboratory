package com.shana.laboratory.index.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaboratoryIndexDraftApproval {
	
	@Id
	private String id;
    private String indexDraftId;
    private String approver;
    private String approvalResult;
    private String approvalOpinion;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndexDraftId() {
		return indexDraftId;
	}
	public void setIndexDraftId(String indexDraftId) {
		this.indexDraftId = indexDraftId;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovalResult() {
		return approvalResult;
	}
	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}
	public String getApprovalOpinion() {
		return approvalOpinion;
	}
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
    
   
}

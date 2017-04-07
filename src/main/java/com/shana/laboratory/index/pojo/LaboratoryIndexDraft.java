package com.shana.laboratory.index.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaboratoryIndexDraft {
	
	@Id
	private String id;
	private String code;
	private String cnName;
	private String enName;
	private String type;
	private Double min;
	private Double max;
	private Double minNormal;
    private Double maxNormal;
    private String unit;
    private String editor;
    private String approver;
    private String approvalResult;
    private String approvalOpinion;
    
    //CREATING  APPROVING  APPROVAL_SUCCESS  APPROVAL_REJECT
    private String status;
    
    
	
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMinNormal() {
		return minNormal;
	}
	public void setMinNormal(Double minNormal) {
		this.minNormal = minNormal;
	}
	public Double getMaxNormal() {
		return maxNormal;
	}
	public void setMaxNormal(Double maxNormal) {
		this.maxNormal = maxNormal;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	

}

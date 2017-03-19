package com.shana.laboratory.sheet.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaboratorySheetItem {
	
	@Id
	private String id;
	private String sheetId;
	private String indexId;
	private String value;
	private String code;
	private String cnName;
	private Double minNormal;
    private Double maxNormal;
    private String unit;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSheetId() {
		return sheetId;
	}
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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

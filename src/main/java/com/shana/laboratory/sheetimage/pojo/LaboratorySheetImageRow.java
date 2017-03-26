package com.shana.laboratory.sheetimage.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaboratorySheetImageRow {
	@Id
	private String id;
	private String imageId;
	private List<LaboratorySheetImageColumn> columns;
	private int maxTop;
	private int minTop;
	private int maxLeft;
	private int minLeft;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public List<LaboratorySheetImageColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<LaboratorySheetImageColumn> columns) {
		this.columns = columns;
	}
	public int getMaxTop() {
		return maxTop;
	}
	public void setMaxTop(int maxTop) {
		this.maxTop = maxTop;
	}
	public int getMinTop() {
		return minTop;
	}
	public void setMinTop(int minTop) {
		this.minTop = minTop;
	}
	public int getMaxLeft() {
		return maxLeft;
	}
	public void setMaxLeft(int maxLeft) {
		this.maxLeft = maxLeft;
	}
	public int getMinLeft() {
		return minLeft;
	}
	public void setMinLeft(int minLeft) {
		this.minLeft = minLeft;
	}
	@Override
	public String toString() {
		//return "\nLaboratorySheetImageRow [id=" + id + ", imageId=" + imageId + ", maxTop="
		//		+ maxTop + ", minTop=" + minTop + ", maxLeft=" + maxLeft + ", minLeft=" + minLeft + ", columns=" + columns + "]";
		
		return "\n\tminTop=" + minTop + ",maxTop="
		+ maxTop + ", minLeft=" + minLeft +", maxLeft=" + maxLeft + ", columns=" + columns + "";
	}
	
	
	
}

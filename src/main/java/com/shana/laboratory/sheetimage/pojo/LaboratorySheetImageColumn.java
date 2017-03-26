package com.shana.laboratory.sheetimage.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaboratorySheetImageColumn {
	@Id
	private String id;
	private String rowId;
	private String words;
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
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
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
		//return "\nLaboratorySheetImageColumn [id=" + id + ", rowId=" + rowId + ", words=" + words + ", maxTop=" + maxTop
			//	+ ", minTop=" + minTop + ", maxLeft=" + maxLeft + ", minLeft=" + minLeft + "]";
		return "\n\t\twords=" + words + ", minTop=" + minTop + ",maxTop=" + maxTop
				+", minLeft=" + minLeft  +", maxLeft=" + maxLeft + "";
	}
	
	
}

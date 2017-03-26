package com.shana.laboratory.sheetimage.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LaboratorySheetImage {
	@Id
	private String id;
	
	@JsonIgnore
	private byte[] imageByte;	
	private String text;
	private String md5;
	private List<LaboratorySheetImageRow> rows;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public byte[] getImageByte() {
		return imageByte;
	}
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public List<LaboratorySheetImageRow> getRows() {
		return rows;
	}
	public void setRows(List<LaboratorySheetImageRow> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "LaboratorySheetImage [id=" + id + ", rows=" + rows + "]";
	}
   
	
	
}

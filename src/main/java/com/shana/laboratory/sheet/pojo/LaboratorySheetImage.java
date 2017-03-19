package com.shana.laboratory.sheet.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LaboratorySheetImage {
	@Id
	private String id;
	private String sheetId;
	private byte[] imageByte;
	private String text;
	private String md5;
	
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
   
	
	
}

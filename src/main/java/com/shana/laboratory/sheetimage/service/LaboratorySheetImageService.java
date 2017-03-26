package com.shana.laboratory.sheetimage.service;

import com.shana.laboratory.sheetimage.pojo.LaboratorySheetImage;

public interface LaboratorySheetImageService {
	
	public LaboratorySheetImage uploadSheetImage(byte[] image) throws Exception;

}

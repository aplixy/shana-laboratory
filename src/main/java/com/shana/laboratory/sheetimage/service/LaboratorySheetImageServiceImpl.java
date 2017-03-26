package com.shana.laboratory.sheetimage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Base64Util;
import com.shana.laboratory.sheetimage.pojo.LaboratorySheetImage;
import com.shana.laboratory.sheetimage.pojo.LaboratorySheetImageColumn;
import com.shana.laboratory.sheetimage.pojo.LaboratorySheetImageRow;

public class LaboratorySheetImageServiceImpl implements LaboratorySheetImageService {
	private static final int LOCATION_PADDING = 0;
	public static final String APP_ID = "";
	public static final String API_KEY = "";
	public static final String SECRET_KEY = "";

	@Override
	public LaboratorySheetImage uploadSheetImage(byte[] image) throws Exception {

		// 初始化一个OcrClient
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		JSONObject genRes = client.general("/Develop/timg.jpeg", new HashMap<String, String>());

		LaboratorySheetImage result = generateLaboratorySheetImage(genRes);
		result.setImageByte(image);
		save(result);
		return result;

	}

	private void save(LaboratorySheetImage result) {
		// TODO Auto-generated method stub

	}

	private LaboratorySheetImage generateLaboratorySheetImage(JSONObject baiduOcrResult) {
		LaboratorySheetImage sheetImage = new LaboratorySheetImage();
		sheetImage.setId(UUID.randomUUID().toString());
		String baiduOcrResultStr = baiduOcrResult.toString(2);
		System.out.println(baiduOcrResult);
		List<LaboratorySheetImageRow> rows = generalLaboratorySheetImageRows(sheetImage.getId(), baiduOcrResult);
		sheetImage.setText(baiduOcrResultStr);
		sheetImage.setRows(rows);
		return sheetImage;
	}

	private List<LaboratorySheetImageRow> generalLaboratorySheetImageRows(String imageId, JSONObject baiduOcrResult) {
		List<LaboratorySheetImageRow> rows = new ArrayList<LaboratorySheetImageRow>();
		JSONArray wordsResult = baiduOcrResult.getJSONArray("words_result");
		int len = wordsResult.length();
		String words = null;
		int minTop, maxTop, minLeft, maxLeft;
		LaboratorySheetImageRow row = null;
		List<LaboratorySheetImageColumn> columns=null;
		LaboratorySheetImageColumn column=null;
		for (int i = 0; i < len; i++) {
			JSONObject item = wordsResult.getJSONObject(i);
			words = item.getString("words");
			JSONObject location = item.getJSONObject("location");
			minTop = location.getInt("top");
			maxTop = minTop + location.getInt("height");
			minLeft = location.getInt("left");
			maxLeft = minLeft +location.getInt("width") ;
			System.out.println("words="+words+","+location);
			System.out.println("words="+words+","+minTop+","+maxTop+","+minLeft+","+maxLeft);
			row = searchRowByLocation(rows, minTop, maxTop, minLeft, maxLeft);
			if (null == row) {
				row = new LaboratorySheetImageRow();
				row.setId(UUID.randomUUID().toString());
				row.setImageId(imageId);
				row.setMaxTop(maxTop+LOCATION_PADDING);
				row.setMinTop(minTop-LOCATION_PADDING);
				row.setMaxLeft(maxLeft+LOCATION_PADDING);
				row.setMinLeft(minLeft-LOCATION_PADDING);
				columns=new ArrayList<LaboratorySheetImageColumn>();
				row.setColumns(columns);
				rows.add(row);
			}
			else
			{
				columns=row.getColumns();
				if(row.getMinLeft()>minLeft)
					row.setMinLeft(minLeft);
				if(row.getMaxLeft()<maxLeft)
					row.setMaxLeft(maxLeft);
				//重设行在minTop,maxTop
			}
			
		    column = new LaboratorySheetImageColumn();
		    column.setId(UUID.randomUUID().toString());
		    column.setRowId(row.getId());
		    column.setWords(words);
		    column.setMaxTop(maxTop);
		    column.setMinTop(minTop);
		    column.setMaxLeft(maxLeft);
		    column.setMinLeft(minLeft);
		    
		    insertColumnIntoRow(columns, column);
		}
		return rows;
	}

	private void insertColumnIntoRow(List<LaboratorySheetImageColumn> columns, LaboratorySheetImageColumn column) {
		int index=0;
		int itemMinLeft,itemMaxLeft,columnMinLeft,columnMaxLeft;
		int len=columns.size();
		LaboratorySheetImageColumn item=null;
		for(int i=0;i<len;i++)
		{
			item=columns.get(i);
			itemMinLeft=item.getMinLeft();
			itemMaxLeft=item.getMaxLeft();
			columnMinLeft=column.getMinLeft();
			columnMaxLeft=column.getMaxLeft();
			if(columnMaxLeft<=itemMinLeft)
			{
				index=i;
				break;
			}
		}
		columns.add(index, column);
	}

	private LaboratorySheetImageRow searchRowByLocation(List<LaboratorySheetImageRow> rows, int minTop, int maxTop,
			int minLeft, int maxLeft) {
		for(LaboratorySheetImageRow row:rows)
		{    
			
			if(minTop>=row.getMinTop()&&maxTop<=row.getMaxTop())
			{
				return row;
			}
			else if(minTop>row.getMinTop()&&minTop<row.getMaxTop())
			{
				//块占领行在一大部分
				int rowInteval=row.getMaxTop()-row.getMinTop();
				if(minTop<(row.getMaxTop()-(rowInteval/2)))
				{
					//minLeft和maxLeft不能和row里任何column有重复
					if(checkColumnIsNotInRow(row, minLeft, maxLeft))
					{
						return row;
					}
				}
				//块有一大部分在行里
			    int inteval=maxTop-minTop;
			    if(row.getMaxTop()-minTop>inteval/2)
			    {
			    	//minLeft和maxLeft不能和row里任何column有重复
					if(checkColumnIsNotInRow(row, minLeft, maxLeft))
					{
						return row;
					}
			    }
			    
				
			}
			else if(maxTop>row.getMinTop()&&maxTop<row.getMaxTop())
			{
				//块占领行在一大部分
				int rowInteval=row.getMaxTop()-row.getMinTop();
				if(maxTop>(row.getMinTop()+(rowInteval/2)))
				{
					//minLeft和maxLeft不能和row里任何column有重复
					if(checkColumnIsNotInRow(row, minLeft, maxLeft))
					{
						return row;
					}
				}
				
				//块有一大部分在行里
			    int inteval=maxTop-minTop;
			    if(maxTop-row.getMinTop()>inteval/2)
			    {
			    	//minLeft和maxLeft不能和row里任何column有重复
					if(checkColumnIsNotInRow(row, minLeft, maxLeft))
					{
						return row;
					}
			    }
			}
		}
		return null;
	}

	private boolean checkColumnIsNotInRow(LaboratorySheetImageRow row, int minLeft, int maxLeft) {
		List<LaboratorySheetImageColumn> columns=row.getColumns();
		int index=0;
		int itemMinLeft,itemMaxLeft;
		int len=columns.size();
		LaboratorySheetImageColumn item=null;
		for(int i=0;i<len;i++)
		{
			item=columns.get(i);
			itemMinLeft=item.getMinLeft();
			itemMaxLeft=item.getMaxLeft();
			if(minLeft>=itemMinLeft&&maxLeft<itemMaxLeft)
			{
				return false;
			}
			else if(minLeft<itemMinLeft&&maxLeft>itemMinLeft)
			{
				return false;
			}
			else if(maxLeft>itemMaxLeft&&minLeft<itemMaxLeft)
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {

		LaboratorySheetImageServiceImpl laboratorySheetImageService = new LaboratorySheetImageServiceImpl();
		LaboratorySheetImage result = laboratorySheetImageService.uploadSheetImage(null);
		System.out.println(result);
	}

	private String getImageStr() {
		String imgData = null;
		File file = new File("/Develop/timg.jpeg");
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] imgByte = IOUtils.toByteArray(in);

			imgData = Base64Util.encode(imgByte);

		} catch (IOException e) {

		} finally {
			if (null != in)
				try {
					in.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
		return imgData;
	}

}

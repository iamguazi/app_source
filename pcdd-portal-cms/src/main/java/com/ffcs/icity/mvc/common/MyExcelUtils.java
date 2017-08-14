package com.ffcs.icity.mvc.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jodd.bean.BeanUtil;
import jodd.datetime.JDateTime;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MyExcelUtils<T> {
	
	private Workbook wb = null;    
	
	public void export(String path,List<T> dataList,String[] headFieldName,String[] columnField,Map<String, Map<String,Object>> replaceFields) throws IOException{
    	if (path.endsWith("xls")) {    
            wb = new HSSFWorkbook();
        }    
        else if(path.endsWith("xlsx"))    
        {    
            wb = new XSSFWorkbook();
        }    
        else    
        {    
            throw new RuntimeException("导出的格式不对");   
        }
    	
        //创建sheet对象     
        Sheet sheet = (Sheet) wb.createSheet("sheet");
        
        //创建头部
        buildHeadData(sheet,headFieldName);
        buildExcelData(sheet,dataList,columnField,replaceFields);
        
        //创建文件流     
        OutputStream stream = new FileOutputStream(path);    
        //写入数据     
        wb.write(stream);    
        //关闭文件流     
        stream.close();    
    }
	
	 private void buildHeadData(Sheet sheet,String[] headFieldName){
		 Font headFont  = wb.createFont();    
	     CellStyle headCellStyle = wb.createCellStyle();
	     headFont.setFontHeightInPoints((short) 15);//设置字体大小
	     headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);  //加粗
	     headCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //居中
	     headCellStyle.setFont(headFont);
		 Row headRow = sheet.createRow(0);
		 Cell cell = null;
	     for(int i=0,len=headFieldName.length;i<len;i++){
	    	cell =  headRow.createCell(i);
	        cell.setCellStyle(headCellStyle);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        cell.setCellValue(headFieldName[i]);
	     }
	 }
	 
	 private void buildExcelData(Sheet sheet, List<T> dataList,String[] columnField,Map<String, Map<String,Object>> replaceFields) {
	        // 从第几行开始插入数据
	        int startRow = 1;
	        Cell cell = null;
	        for (Object obj : dataList) {
	            Row row = sheet.createRow(startRow++);
	            for (int j = 0,len=columnField.length; j < len; j++) {
	            	cell = row.createCell(j);
	            	String filedName = columnField[j];
	            	Object valueObject = BeanUtil.getProperty(obj, columnField[j]);
                    
                    // 如果存在需要转换的字段信息，则进行转换
                    if(replaceFields!=null && replaceFields.containsKey(filedName)){
                    	valueObject = replaceFields.get(filedName).get(valueObject.toString());
                    }
                     
                    if(valueObject == null) {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue("");
                    } else if (valueObject instanceof Integer) {
                    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue((Integer)valueObject);
                    } else if (valueObject instanceof String) {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue((String)valueObject);
                    } else if (valueObject instanceof Date) {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(new JDateTime((Date)valueObject).toString("YYYY-MM-DD hh:mm:ss"));
//                        cell.setCellValue(new JDateTime((Date)valueObject).toString());
                    } else {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(valueObject.toString());
                    }
                    
	            }
	                
	        }
	    }
	 
	 
	 public static void main(String[] args) throws IOException {
//		 MyExcelUtils<RechargeCard> excelUtil = new MyExcelUtils<RechargeCard>();
//		 String path = "d://recharge.xlsx";
//		 String[] heads = new String[]{"充值卡密","充值金额","创建日期","是否印记","状态"};
//		 String[] columnField = new String[]{"cardPassword","cardFee","createTime","isPrinter","cardStatus"};
//		 Map<String,Map<String,Object>> replaceField = new HashMap<String,Map<String,Object>>();
//		 Map<String,Object> isPrinter = new HashMap<String,Object>();
//		 isPrinter.put("1","是");
//		 isPrinter.put("0","否");
//		 replaceField.put("isPrinter",isPrinter);
//		 List<RechargeCard> list = new ArrayList<RechargeCard>();
//		 RechargeCard rc = null;
//		 Random r = new Random();
//		 for(int i=0;i<10000;i++){
//			 rc = new RechargeCard();
//			 rc.setCardFee(r.nextDouble());
//			 rc.setCardPassword(MyUuid.uuid());
//			 rc.setCreateTime(new Date());
//			 rc.setIsPrinter(i%2);
//			 list.add(rc);
//		 }
//		 excelUtil.export(path,list,heads,columnField,replaceField);
	}
	 
	 public void downloadXls(String path, HttpServletResponse response) throws IOException {
	        File file = null;
	        OutputStream toClient = null;
//	        InputStream fis = null;
	        try {
	        	 // path是指欲下载的文件的路径。
	            file = new File(path);
	            // 取得文件名。
	            String filename = file.getName();
	            // 以流的形式下载文件。
	            InputStream fis = new BufferedInputStream(new FileInputStream(path));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename="
	                    + new String(filename.getBytes()));
	            response.addHeader("Content-Length", "" + file.length());
	            toClient = new BufferedOutputStream(
	                    response.getOutputStream());
	            response.setContentType("application/vnd.ms-excel;charset=utf-8");
	            toClient.write(buffer);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            if(null != file){
	                file.delete();
	            }
	            if(null != toClient){
	                toClient.flush();
	                toClient.close();
	            }
	        }
	    } 
}

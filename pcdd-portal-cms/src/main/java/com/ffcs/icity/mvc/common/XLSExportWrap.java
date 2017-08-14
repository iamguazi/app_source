package com.ffcs.icity.mvc.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * POI导出Excel
 * 
 * @date: 2014-10-13 下午3:58:43
 * @version: 1.00.00 
 * @history:
 *
 * @param <T>
 */
public class XLSExportWrap<T> {
    
    private String DATE_FPRMAT = "yyyy-MM-dd HH:mm:ss"; // 定制日期格式
    
    private HSSFWorkbook    workbook;       // 工作簿  
    private HSSFSheet       sheet;          // 表格
    private HSSFCellStyle   headerStyle;    // 标题样式
    private HSSFFont        headerFont;     // 标题字体
    private HSSFCellStyle   cellStyle;      // 单元格样式
    private HSSFFont        cellFont;       // 单元格字体
    

    @SuppressWarnings("deprecation")
	public void exportExcel(String sheetTitle, String[] headers, String[] properties, List<T> dataList, OutputStream out) throws IOException{
        this.initHSSF();
        
        int rownum = 0;
        
        // 设置sheet表格的标题
        workbook.setSheetName(0, sheetTitle);
        
        // 设置标题栏
        if(null != headers){
            HSSFRow row = sheet.createRow(rownum ++);
            short colNum = 0;
            for(int l = headers.length; colNum < l; colNum ++){
                HSSFCell header = row.createCell(colNum);
                HSSFFont font = workbook.createFont();
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                font.setFontHeightInPoints((short) 12);
                HSSFCellStyle style = workbook.createCellStyle();
                style.setAlignment(HSSFCellStyle.ALIGN_LEFT);                       // 水平居中
                style.setFont(font);
                header.setCellStyle(style);
                
                HSSFRichTextString text = new HSSFRichTextString(headers[colNum]);
                header.setCellValue(text);
            }
        }
        
        // 遍历集合数据，产生数据行
        if(null != dataList){
            for(T t: dataList){
                this.fillRow(sheet.createRow(rownum ++), t, properties, true);
            }
        }
        workbook.write(out);
    }
    
    /**
     * 初始化数据空间
     * 
     * @date: 2014-10-13 下午2:35:12
     * @version: 1.00.00
     * @history:
     */
    private void initHSSF(){
        if(null == workbook){
            workbook = new HSSFWorkbook();
        }
        if(null == sheet){
            sheet = this.setHSSFSheetDefaults(workbook.createSheet());
            
        }
        if(null == headerFont){
            headerFont = this.createHeaderFont(workbook);
        }
        if(null == headerStyle){
            headerStyle = this.createHeaderStyle(workbook);
            headerStyle.setFont(headerFont);
        }
        if(null == cellFont){
            cellFont = this.createCellFont(workbook);
        }
        if(null == cellStyle){
            cellStyle = this.createCellStyle(workbook);
            cellStyle.setFont(cellFont);
        }
    }
    
    
    /**
     * 设置表格默认属性
     * @param sheet
     * @return
     * @date: 2014-10-13 上午9:24:06
     * @version: 1.00.00
     * @history:
     */
    @SuppressWarnings("deprecation")
	private HSSFSheet setHSSFSheetDefaults(HSSFSheet sheet){
        sheet.setDefaultRowHeightInPoints(18);
        sheet.setDefaultColumnWidth((short) 20);
        return sheet;
    }
    /**
     * 创建表头样式
     * @param workbook
     * @return
     * @date: 2014-10-13 上午9:28:10
     * @version: 1.00.00
     * @history:
     */
    private HSSFCellStyle createHeaderStyle(HSSFWorkbook  workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);                       // 水平居中
        style.setIndention((short) 1);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);          // 上下居中
        HSSFDataFormat hDateFormat = workbook.createDataFormat();   
        style.setDataFormat(hDateFormat.getFormat("0.00%"));                // 设置单元格数据格式
        style.setFillForegroundColor(HSSFColor.YELLOW.index);               // 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);               // 填充颜色
        return style;
    }
    /**
     * 创建表头字体
     * @param workbook
     * @return
     * @date: 2014-10-13 上午9:29:50
     * @version: 1.00.00
     * @history:
     */
    private HSSFFont createHeaderFont(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFFont.COLOR_NORMAL);
        font.setFontHeightInPoints((short) 14);
        return font;
    }
    /**
     * 创建单元个样式
     * @param workbook
     * @return
     * @date: 2014-10-13 上午9:28:23
     * @version: 1.00.00
     * @history:
     */
    private HSSFCellStyle createCellStyle(HSSFWorkbook  workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        style.setIndention((short) 2);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        return style;
    }
    /**
     * 创建单元个字体
     * @param workbook
     * @return
     * @date: 2014-10-13 上午9:30:10
     * @version: 1.00.00
     * @history:
     */
    private HSSFFont createCellFont(HSSFWorkbook workbook){
        HSSFFont font = workbook.createFont();
        
        return font;
    }
    
    /**
     * 反射机制获取javabean属性的值，该对象需要有getter 或者 isser
     * @param t
     * @param field
     * @return
     * @date: 2014-10-13 下午2:36:07
     * @version: 1.00.00
     * @history:
     */
    private Object doGetValue(T t, Field  field){
        String methodName;
        String fieldName = field.getName();
        Object value = "";
        if(field.getType() == boolean.class){
            methodName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        } else{
            methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        try {
            Class<?> cls = t.getClass();
            Method method = cls.getMethod(methodName, new Class[]{});
            value = method.invoke(t, new Object[]{});
        } catch (Exception e) {
            System.err.print("get value error(" + fieldName + "): " + e.getMessage());
        }
        return value == null ? "" : value;
    }
    /**
     * 填充表格行数据
     * @param row
     * @param t
     * @param properties
     * @param backParents
     * @return
     * @date: 2014-10-13 下午2:37:18
     * @version: 1.00.00
     * @history:
     */
    @SuppressWarnings("deprecation")
	private HSSFRow fillRow(HSSFRow row, T t, String[] properties, boolean backParents){
        if(properties != null && properties.length > 0 && t != null && row != null){
            HSSFCell cell;
            Field field;
            Object value;
            String textValue = null;
            HSSFRichTextString richString;
            short colNum = 0;
            for(int l = properties.length; colNum < l; colNum++){
                field = this.getFieldByName(t, properties[colNum], backParents);
                if(null == field){
                    continue;
                }
                cell = row.createCell(colNum);
//                cell.setCellStyle(cellStyle);
                value = this.doGetValue(t, field);
                
                if(value instanceof Date){      // 日期类型的数据作格式化操作
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(this.DATE_FPRMAT);
                    textValue = sdf.format(date);
                } else {
                    textValue = String.valueOf(value);
                }
                    
                if(null != textValue){
                    richString = new HSSFRichTextString(textValue);
                    richString.applyFont(cellFont);
                    cell.setCellValue(richString);
                }
            }
        }
        return row;
    }
    
    /**
     * 根据属性名称获取javabean的属性对象
     * @param t javabean对象
     * @param fieldName 属性名称
     * @param backParents 是否追溯到父类
     * @return
     * @date: 2014-10-13 下午2:38:31
     * @version: 1.00.00
     * @history:
     */
    private Field getFieldByName(T t, String fieldName, boolean backParents){
        if(null != t && !StringUtils.isBlank(fieldName)){
            Class<?> cls = t.getClass();
            Field field = null;
            do{
                try {
                    field = cls.getDeclaredField(fieldName);
                } catch (Exception e) {
                    cls = cls.getSuperclass();
                }
            } while(field == null && backParents && cls != Object.class);
            return field;
        }
        return null;
    }
    
    
    public static void downloadXls(String path,HttpServletRequest request, HttpServletResponse response,boolean isDeleteFile) throws IOException {
        File file = null;
        OutputStream toClient = null;
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
            response.setCharacterEncoding("utf-8"); 
            
            String userAgent = request.getHeader("User-Agent").toLowerCase();
            if(userAgent.indexOf("firefox") > -1){
            	response.addHeader("Content-Disposition", "attachment;filename="
                        + new String(filename.getBytes(),"ISO8859-1"));
            }else{
            	response.addHeader("Content-Disposition", "attachment;filename="
                        + URLEncoder.encode(filename, "UTF-8")) ;
            }
            
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            toClient.write(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(null != file && isDeleteFile){
                file.delete();
            }
            if(null != toClient){
                toClient.flush();
                toClient.close();
            }
        }
    } 
    
    public static void downloadXls(String path,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	downloadXls( path,request, response,true);
    }
//    public static void main(String[] args) throws IOException {
//        XLSExportWrap<Account> w = new XLSExportWrap<Account>();
//        
//        List<Account> l = new ArrayList<Account>();
//        for(int i = 0; i < 100; i ++){
//            Account a = new Account();
//            a.setWorkno("" + i);
//            a.setName("名字" + i);
//            a.setRank("职务" + i);
//            l.add(a);
//        }
//        
//        File f = new File("f:\\a.xls");
//        OutputStream os = new FileOutputStream(f); 
//        w.exportExcel("这是标题", new String[]{"工号", "名字", "职务"}, new String[]{"workno", "name", "rank"}, l, os);
//    }

}

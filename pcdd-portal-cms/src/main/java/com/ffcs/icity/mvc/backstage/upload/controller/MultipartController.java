package com.ffcs.icity.mvc.backstage.upload.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ffcs.icity.mvc.asyn.constant.BaseConstant;
import com.ffcs.icity.mvc.common.FileUploadUtil;

@Controller
@RequestMapping("/backstage/multipart/")
public class MultipartController {

    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${imgServerRoot}")
    private String imgServerRoot;           // 图片存储服务器根目录
    
    @Value("${imgSaveRoot:upload/}")
    private String imgSaveRoot;             // 数据库存储的图片根目录
    
    @Value("${defaultBussinessDir:defaultBussinessDir/}")    
    private String defaultBussinessDir;     // 默认的图片存储目录
    
    @Value("${imgShowRoot}")
    private String imgShowRoot;             // 图片展示的服务器路径
    
    @Value("${imgTypes:|jpg|jpeg|gif|bmp|png|}")
    private String imgTypes;				// 支持的图片类型
    
    @Value("${imgSize:2}")
    private Long imgSize;				// 支持的图片大小(M)
    
    
    @RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bussinessDir = request.getParameter("bussinessDir");
        String nowStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String savePath = imgSaveRoot  + (StringUtils.isBlank(bussinessDir) ? defaultBussinessDir : bussinessDir)+nowStr+"/";
        logger.debug("savePath = " + savePath);
        JSONObject resJson = new JSONObject();
        try {
        	boolean isValid = this.isValidImgFile(request);
        	logger.debug("isValid = " + isValid);
        	if(!isValid){
        		resJson.put(BaseConstant.STATUS, BaseConstant.SERVICE_EXCEPTION);
        		resJson.put(BaseConstant.DESC, "仅支持" + imgSize + "M " + imgTypes + " 类型图片");
        	}else{
        		String[] filePaths = FileUploadUtil.uploadFile(request, imgServerRoot, savePath);
                JSONObject json = new JSONObject();
                json.put("paths", JSONArray.fromObject(filePaths));
                json.put("imgShowRoot", imgShowRoot);
                
                resJson.put(BaseConstant.STATUS, BaseConstant.SUCCESS_FOR_DATA);
                resJson.put(BaseConstant.DESC, BaseConstant.SUCCESS_FOR_DATA_DESC);
                resJson.put(BaseConstant.DATA, json);
        	}
        } catch (Exception e) {
            logger.error(e.getMessage());
            resJson.put(BaseConstant.STATUS, BaseConstant.SERVICE_EXCEPTION);
            resJson.put(BaseConstant.DESC, BaseConstant.SERVICE_EXCEPTION_DESC);
        }
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(JSONObject.fromObject(resJson).toString());
    }
    
    @RequestMapping("ckeditorUpload")
    public void ckeditorUpload(MultipartHttpServletRequest multipartRequest,
            HttpServletResponse response) throws Exception {
    	String nowStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String savePath = imgServerRoot + imgSaveRoot + "ckeditor/content/"+nowStr+"/";

        if(this.isValidImgFile(multipartRequest)){
        	
        	String[] filePaths = FileUploadUtil.uploadFile(multipartRequest, savePath);
            PrintWriter out = response.getWriter();
            String callback = multipartRequest.getParameter("CKEditorFuncNum");
            if (null != filePaths && filePaths.length > 0) {
                String imgRealPath = imgShowRoot + imgSaveRoot + "ckeditor/content/" +nowStr+"/"+ filePaths[0];
                response.setContentType("text/html; charset=utf-8");
                out.println("<script type=\"text/javascript\">");
                out.println("if(window.parent.imgPathList)window.parent.imgPathList.push('" + imgRealPath + "');");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imgRealPath + "','')");
                out.println("</script>");
            }
    	}else{
    		String callback = multipartRequest.getParameter("CKEditorFuncNum");
    		PrintWriter out = response.getWriter();
    		response.setContentType("text/html; charset=utf-8");
            out.println("<script type=\"text/javascript\">");
            
//            out.println("alert('图片大小限制"+imgSize+"M');");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'仅支持 " + imgSize + "M " + imgTypes + " 类型图片');");   
            out.println("</script>");  
            out.println("</script>");
    	}
    }
    
    
    private boolean isValidImgFile(HttpServletRequest request){
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
    	Long size = imgSize * 1024 * 1024;
    	for(Iterator<String> fileNameIterator = multipartRequest.getFileNames(); fileNameIterator.hasNext();){
                String name = fileNameIterator.next();
                MultipartFile file = multipartRequest.getFile(name);
                String fileName = file.getOriginalFilename();
                if(file.getSize() > size){
                	return false;
                }
                int index = -1;
                String ext = "||";
                if((index = fileName.lastIndexOf(".")) > -1){
                    ext = "|" + fileName.substring(index + 1).toLowerCase() + "|";
                }
                if(imgTypes.indexOf(ext) == -1){
                	return false;
                }
    	}
    	return true;
    }
    
    
    /**
     * 保存上传的文件
     * @param request
     * @param bussinessDir
     * @return
     * @throws Exception
     */
    public String[] upload(HttpServletRequest request,String bussinessDir) throws Exception {
        String nowStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String savePath = imgSaveRoot  + (StringUtils.isBlank(bussinessDir) ? defaultBussinessDir : bussinessDir)+nowStr+"/";
        logger.debug("savePath = " + savePath);
        String[] filePaths = FileUploadUtil.uploadFile(request, imgServerRoot, savePath);
        return filePaths;
    }
    
    /**
     * 验证图片类型
     * @param multipartRequest
     * @param maxfileSize  文件大小(单位:M)
     * @param acceptExtendedNames 接受的文件,以"|"符号开始和结束  如:|jpg|png|
     * @return
     */
    public  boolean isValidFile(MultipartHttpServletRequest multipartRequest,long maxfileSize,String acceptExtendedNames){
    	Long byteSize = maxfileSize * 1024 * 1024;
    	for(Iterator<String> fileNameIterator = multipartRequest.getFileNames(); fileNameIterator.hasNext();){
                String name = fileNameIterator.next();
                MultipartFile file = multipartRequest.getFile(name);
                String fileName = file.getOriginalFilename();
                if(file.getSize() > byteSize){
                	return false;
                }
                int index = -1;
                String ext = "||";
                if((index = fileName.lastIndexOf(".")) > -1){
                    ext = "|" + fileName.substring(index + 1).toLowerCase() + "|";
                }
                if(acceptExtendedNames.indexOf(ext) == -1){
                	return false;
                }
    	}
    	return true;
    }

    
    
    
    
    
	public String getImgServerRoot() {
		return imgServerRoot;
	}

	public void setImgServerRoot(String imgServerRoot) {
		this.imgServerRoot = imgServerRoot;
	}

	public String getImgSaveRoot() {
		return imgSaveRoot;
	}

	public void setImgSaveRoot(String imgSaveRoot) {
		this.imgSaveRoot = imgSaveRoot;
	}

	public String getDefaultBussinessDir() {
		return defaultBussinessDir;
	}

	public void setDefaultBussinessDir(String defaultBussinessDir) {
		this.defaultBussinessDir = defaultBussinessDir;
	}
    
    
    
}

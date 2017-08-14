package com.ffcs.icity.mvc.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadUtil {

    public static String FILE_NAME = "upload_file";
    
    
    public static String[] uploadFile(HttpServletRequest request, String rootDir, String moduleDir) throws Exception{
        String[] filePath = FileUploadUtil.uploadFile(request, rootDir + moduleDir);
        if(!moduleDir.endsWith("/")){
            moduleDir = moduleDir + "/"; 
        }
        for (int i = 0, l = filePath.length; i < l; i++) {
            filePath[i] = moduleDir + filePath[i];
        }
        return filePath;
    }
    
    public static String[] uploadFile(HttpServletRequest request, String savePath) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<String> fileNameList = new ArrayList<String>();
        for(Iterator<String> fileNameIterator = multipartRequest.getFileNames(); fileNameIterator.hasNext();){
            String fileName = fileNameIterator.next();
            fileNameList.add(FileUploadUtil.uploadFile(multipartRequest.getFile(fileName), savePath));
        }
        String[] fileNames = new String[fileNameList.size()];
        for (int i = 0, l = fileNameList.size(); i < l; i++) {
            fileNames[i] = fileNameList.get(i);
        }
        return fileNames;
    }
    
    
    public static String uploadFile(MultipartFile multipartFile, String savePath) throws Exception{
        File file = FileWrapper.saveFile(multipartFile, savePath, "upload");
        return file.getName();
    }
    
    
    public static boolean isValidExt(MultipartHttpServletRequest multipartRequest, String[] exts) {
    	List<String> extList = new ArrayList<String>();
    	for (String ext : exts) {
    		extList.add(ext.toLowerCase());
		}
    	for(Iterator<String> fileNameIterator = multipartRequest.getFileNames(); fileNameIterator.hasNext();){
            String fileName = fileNameIterator.next();
            MultipartFile file = multipartRequest.getFile(fileName);
            String orginalName = file.getOriginalFilename();
            int index = -1;
            if ((index = orginalName.lastIndexOf(".")) == -1) {
            	return false;
            } else {
            	String fileExt = orginalName.substring(index + 1).toLowerCase();
            	if (!extList.contains(fileExt)){
            		return false;
            	}
            }
    	}
    	return true;
    }
    
}

package com.ffcs.icity.api.module.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadUtil {

    public static String FILE_NAME = "upload_file";
    
    
    public static String[] uploadFile(HttpServletRequest request, String rootDir, String moduleDir) throws Exception{
        String[] filePath = FileUploadUtil.uploadFile(request, rootDir + moduleDir);
        for (int i = 0, l = filePath.length; i < l; i++) {
            if(!StringUtils.isBlank(filePath[i])){
                filePath[i] = moduleDir + filePath[i];
            }
        }
        return filePath;
    }
    
    public static String[] uploadFile(HttpServletRequest request, String savePath) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<String> fileNameList = new ArrayList<String>();
        for(Iterator<String> fileNameIterator = multipartRequest.getFileNames(); fileNameIterator.hasNext();){
            String fileName = fileNameIterator.next();
            MultipartFile multipartFile = multipartRequest.getFile(fileName);
            if(null != multipartFile && !multipartFile.isEmpty()){
                fileNameList.add(FileUploadUtil.uploadFile(multipartFile, savePath));
            }else{
                fileNameList.add("");
            }
        }
        String[] fileNames = new String[fileNameList.size()];
        for (int i = 0, l = fileNameList.size(); i < l; i++) {
            fileNames[i] = fileNameList.get(i);
        }
        return fileNames;
    }
    
    
    public static String uploadFile(MultipartFile multipartFile, String savePath) throws Exception{
        File file = FileWrapper.saveFile(multipartFile, savePath, "userPhoto");
        return file.getName();
    }
}

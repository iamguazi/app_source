package com.ffcs.icity.mvc.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileWrapper {
    
    private static Logger logger = LoggerFactory.getLogger(FileWrapper.class);
    
    /**
     * 判断path对应的File是否存在
     * @param path
     * @return
     * @date: 2014-7-31 下午4:31:32
     * @version: 1.00.00
     * @history:
     */
    public static boolean isExit(String path) {
        boolean b = StringUtils.isBlank(path) ? false : (new File(path)).exists();
        logger.debug(" File path : { " + path + " } is exits : " + b);
        return b; 
    }
    /**
     * 判断filePath对应的File是否为文件
     * @param filePath
     * @return
     * @date: 2014-7-31 下午4:31:56
     * @version: 1.00.00
     * @history:
     */
    public static boolean isFile(String filePath) {
        boolean b = !FileWrapper.isExit(filePath) ?  false : (new File(filePath)).isFile();
        logger.debug(" File path : { " + filePath + " } is File : " + b);
        return b; 
    }
    /**
     * 判断dirPath对应的File是否为目录
     * @param dirPath
     * @return
     * @date: 2014-7-31 下午4:33:20
     * @version: 1.00.00
     * @history:
     */
    public static boolean isDirectory(String dirPath) {
        boolean b = !FileWrapper.isExit(dirPath) ?  false : (new File(dirPath)).isDirectory();
        logger.debug("Directory path : { " + dirPath + " } is Directory : " + b);
        return b; 
    }
    /**
     * 当且仅当filePath对应的File为文件时，删除该文件
     * @param filePath
     * @return
     * @date: 2014-7-31 下午4:33:44
     * @version: 1.00.00
     * @history:
     */
    public static boolean deleteFile(String filePath) {
        boolean b = FileWrapper.isFile(filePath) && (new File(filePath)).delete(); 
        logger.debug("Delete file : { " + filePath + " } , result : " + b);
        return b;
    }
    /**
     * 当且仅当dirPath对应的File为目录时，删除该目录(包括目录下的子文件)
     * @param dirPath
     * @return
     * @date: 2014-7-31 下午4:34:38
     * @version: 1.00.00
     * @history:
     */
    public static boolean deleteDirectory(String dirPath) {
        boolean b = FileWrapper.isDirectory(dirPath);
        if(b == true){
            for (File file : (new File(dirPath)).listFiles()) {
                if(file.isFile()){
                    file.delete();
                } else if(file.isDirectory()){
                    FileWrapper.deleteDirectory(file.getAbsolutePath());
                }
            }
            b = (new File(dirPath)).delete();
        }
        logger.debug("Delete directory : { " + dirPath + " } , result : " + b);
        return b;
    }
    /**
     * 删除目录/文件
     * @param path
     * @return
     * @date: 2014-7-31 下午5:02:50
     * @version: 1.00.00
     * @history:
     */
    public static boolean deleteFileOrDirectory(String path){
        boolean b = FileWrapper.isExit(path);
        if(b == true){
            if(FileWrapper.isFile(path)){
                b = FileWrapper.deleteFile(path);
            } else if(FileWrapper.isDirectory(path)){
                b = FileWrapper.deleteDirectory(path);
            }
        }
        logger.debug("Delete file or directory : { " + path + " }, result : " + b);
        return b;
    }
    /**
     * 组装文件路径 = 文件相对路径{splitFlag} + 系统文件分隔符{java.io.File.separator} + 
     *              标识符{splitFlag} + "_" + System.currentTimeMillis() + "." + 后缀名{ext}
     * @param fileRelativePath
     * @param splitFlag
     * @param ext
     * @return
     * @date: 2014-7-31 下午4:38:29
     * @version: 1.00.00
     * @history:
     */
    public static String formFilePath(String fileRelativePath, String splitFlag, String ext){
        StringBuffer filePath = new StringBuffer();
        if(!StringUtils.isBlank(fileRelativePath)){
            filePath.append(fileRelativePath).append(java.io.File.separator);
        }
        if(!StringUtils.isBlank(splitFlag)){
            long timestamp = System.currentTimeMillis();
            String time = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date(timestamp));
            filePath.append(splitFlag).append("_" + time + "_" + System.currentTimeMillis());
        }
        if(!StringUtils.isBlank(ext)){
            filePath.append(".").append(ext);
        }
        logger.debug("Form filepath : { fileRelativePath : " + fileRelativePath + ", splitFlag : " + splitFlag + ", ext : " + ext + " } ");
        logger.debug("File separator : { " + java.io.File.separator + " }");
        logger.debug("To filePath : " + filePath.toString());
        return filePath.toString();
    }
    /**
     * 创建一个新的文件，为WEB文件上传时需要生成新文件而使用
     * @param fileRelativePath
     * @param splitFlag
     * @param multipartFile
     * @return
     * @date: 2014-7-31 下午4:42:07
     * @version: 1.00.00
     * @history:
     */
    public static File newFile(String fileRelativePath, String splitFlag, MultipartFile multipartFile) {
        String ext = "";
        int index = -1;
        if(null != multipartFile){
            String originalName = multipartFile.getOriginalFilename();
            if((index = originalName.lastIndexOf(".")) > -1){
                ext = originalName.substring(index + 1);
            }
        }
        String filePath = FileWrapper.formFilePath(fileRelativePath, splitFlag, ext);
        File newFile = new File(filePath);
        if(!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                logger.error("NewFile create new file for web fail , e : {} ", e.getMessage() );
            }
        }
        logger.debug("NewFile properties: { multipartFile : " + multipartFile.toString() + ", ext : " + ext + ", filePath : " + filePath + " }");
        return newFile;
    }
    /**
     * 载入path对应的File，如果不存在则创建一个
     * @param path
     * @return
     * @throws IOException
     * @date: 2014-7-31 下午4:43:28
     * @version: 1.00.00
     * @history:
     */
    public static File newFile(String path) throws IOException {
        logger.debug("NewFile path : { " + path + " }");
        if(!StringUtils.isBlank(path)){
            File file = new File(path);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            return file;
        }
        return null;
    }
    /**
     * 将输入流写入到outputFile文件中
     * @param inputStream
     * @param outputFile
     * @throws IOException
     * @date: 2014-7-31 下午4:46:13
     * @version: 1.00.00
     * @history:
     */
    public static void saveFileFromStream(InputStream inputStream, File outputFile) throws IOException  {
        FileOutputStream fos = new FileOutputStream(outputFile);
        byte[] bytes = new byte[1024];
        int byteread = -1;
        while((byteread = inputStream.read(bytes)) != -1){
            fos.write(bytes, 0, byteread);
            fos.flush();
        }
        try {
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        logger.debug("SaveFileFromStream properties : { cacheSize: 1024, fileName: " + outputFile.getName() + " }");
    }
    /**
     * 保存文件，为WEB的文件上传而使用
     * @param multipartFile
     * @param fileRelativePath
     * @param splitFlag
     * @throws IOException
     * @date: 2014-7-31 下午4:46:49
     * @version: 1.00.00
     * @history:
     */
    public static File saveFile(MultipartFile multipartFile, String fileRelativePath, String splitFlag) throws IOException {
        File outputFile = FileWrapper.newFile(fileRelativePath, splitFlag, multipartFile);
        FileWrapper.saveFileFromStream(multipartFile.getInputStream(), outputFile);
        logger.debug("SaveFile properties : { multipartFile : " + multipartFile + ", fileRelativePath : " + fileRelativePath + ", splitFlag" + splitFlag + " }");
        return outputFile;
    }
    /**
     * 当且仅当fromFile为文件时，移动该文件到toFile
     * @param fromFile
     * @param toFile
     * @param delFromFile 是否删除原文件
     * @date: 2014-7-31 下午4:47:22
     * @version: 1.00.00
     * @history:
     */
    public static void moveFileTo(File fromFile, File toFile, boolean delFromFile){
        if(fromFile.isFile()){
            fromFile.renameTo(toFile);
            if(delFromFile){
                fromFile.delete();
            }
            logger.debug("Move fromFile { " + fromFile.getName() + " } to { " + toFile.getName() + " }, delFromFile : " + delFromFile);
        } else {
            logger.debug("Move fromFile is not a file");
        }
    }
    /**
     * 移动目录/文件
     * @param fromDir
     * @param toDir
     * @param delFromDir
     * @date: 2014-7-31 下午4:50:19
     * @version: 1.00.00
     * @history:
     */
    public static void moveDirectoryTo(File fromDir, File toDir, boolean delFromDir) {
        if(fromDir.isDirectory()){
            File[] files = fromDir.listFiles();
            for (File file : files) {
                String filePath = toDir.getAbsolutePath() + java.io.File.separator + file.getName();
                try {
                    FileWrapper.moveDirectoryTo(file, FileWrapper.newFile(filePath), delFromDir);
                } catch (IOException e) {
                    logger.error("Move file fail: { fromFile : " + file.getName() + ", toFile : " + filePath + " }");
                    logger.error("message : {}", e.getMessage());
                }
            }
        } else {
            FileWrapper.moveFileTo(fromDir, toDir, delFromDir);
        }
        
    }
    /**
     * 移动文件/目录
     * @param fromFile
     * @param toFile
     * @date: 2014-7-31 下午4:50:43
     * @version: 1.00.00
     * @history:
     */
    public static void moveTo(File fromFile, File toFile){
        if(null == fromFile){
            logger.debug("fromFile is null");
            return ;
        }
        if(null == toFile){
            logger.debug("toFile is null");
            return ;
        }
        if(fromFile.isFile()){
            FileWrapper.moveFileTo(fromFile, toFile, true);
        } else if(fromFile.isDirectory()){
            FileWrapper.moveDirectoryTo(fromFile, toFile, true);
        }
    }
    /**
     * 移动fromPath对应的File到toPath对应的File
     * @param fromPath
     * @param toPath
     * @date: 2014-7-31 下午4:51:02
     * @version: 1.00.00
     * @history:
     */
    public static void moveTo(String fromPath, String toPath){
        if(!StringUtils.isBlank(fromPath) && StringUtils.isBlank(toPath)){
            try {
                FileWrapper.moveTo(new File(fromPath), FileWrapper.newFile(toPath));
            } catch (IOException e) {
                logger.error("MoveTo create toFile fail , e : ", e.getMessage());
            }
        }
        logger.debug("MoveTo properties : { fromPath :" + fromPath + ", toPath : " + toPath);
    }
    /**
     * 复制目录/文件
     * @param fromFile
     * @param toFile
     * @date: 2014-7-31 下午4:56:30
     * @version: 1.00.00
     * @history:
     */
    public static void copyTo(File fromFile, File toFile){
        if(null == fromFile){
            logger.debug("fromFile is null");
            return ;
        }
        if(null == toFile){
            logger.debug("toFile is null");
            return ;
        }
        if(fromFile.isFile()){
            FileWrapper.moveFileTo(fromFile, toFile, false);
        } else if(fromFile.isDirectory()){
            FileWrapper.moveDirectoryTo(fromFile, toFile, false);
        }
    }
    /**
     * 复制fromFile对应的File到toPath对应的File
     * @param fromPath
     * @param toPath
     * @date: 2014-7-31 下午4:56:49
     * @version: 1.00.00
     * @history:
     */
    public static void copyTo(String fromPath, String toPath){
        if(!StringUtils.isBlank(fromPath) && StringUtils.isBlank(toPath)){
            try {
                FileWrapper.copyTo(new File(fromPath), FileWrapper.newFile(toPath));
            } catch (IOException e) {
                logger.error("MoveTo create toFile fail , e : ", e.getMessage());
            }
        }
        logger.debug("MoveTo properties : { fromPath :" + fromPath + ", toPath : " + toPath);
    }
    /**
     * 转换工程上下文的路径为系统文件路径，通常用于获取webapp下的图片的系统文件路径
     * @param request
     * @param relativePath 包含工程上下文路径的字符串
     * @return
     * @date: 2014-10-20 下午5:10:42
     * @version: 1.00.00
     * @history:
     */
    public static String getFileSystemPathFromContext(HttpServletRequest request, String relativePath){
        String contextPath = request.getContextPath();
        if(!StringUtils.isBlank(relativePath) && relativePath.indexOf(contextPath) == -1){
            String contextFilePath = request.getSession().getServletContext().getRealPath(File.separator);
            if(null == contextFilePath || StringUtils.isBlank(contextFilePath)){
                contextFilePath = request.getSession().getServletContext().getRealPath("/");
            }
            return contextFilePath + relativePath.replace(contextPath, "");
        }else{
            return relativePath;
        }
    }
    
    public static void main(String[] args) {
        String p = "e:/a";
        FileWrapper.deleteDirectory(p);
    }
}

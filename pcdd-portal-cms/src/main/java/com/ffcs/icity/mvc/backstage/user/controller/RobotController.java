package com.ffcs.icity.mvc.backstage.user.controller;

import static com.ffcs.icity.mvc.utils.RequestUtils.resolveParams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.bean.BeanUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ffcs.icity.mvc.asyn.constant.Result;
import com.ffcs.icity.mvc.asyn.controller.AsynController;
import com.ffcs.icity.mvc.backstage.upload.controller.MultipartController;
import com.ffcs.icity.mvc.backstage.user.dao.UserInfoDao;
import com.ffcs.icity.mvc.backstage.user.entity.RobotXls;
import com.ffcs.icity.mvc.backstage.user.entity.UserInfo;
import com.ffcs.icity.mvc.backstage.user.service.UserInfoService;
import com.ffcs.icity.mvc.backstage.userlevel.dao.UserLevelDao;
import com.ffcs.icity.mvc.backstage.userlevel.entity.UserLevel;
import com.ffcs.icity.mvc.common.XLSExportWrap;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.Page;


@RequestMapping("backstage/robot")
@Controller
public class RobotController extends AsynController<UserInfo>{
	
	private static final Logger logger = LoggerFactory.getLogger(RobotController.class);
	
	@Value("${imgShowRoot}")
	private String imgShowRoot;
	
	@Value("${imgServerRoot}")
	private String imgServerRoot; 
	
	@Value("${imgSaveRoot:upload/}")
	private String imgSaveRoot;             // 数据库存储的图片根目录
	    
	@Value("${defaultBussinessDir:defaultBussinessDir/}")    
	private String defaultBussinessDir;
	
	@Value("${batchImportRobotUrl}")
	private String batchImportRobotUrl;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserLevelDao userLevelDao;
	
	private UserInfoDao userInfoDao;

	@Override
	protected CRUDDao<UserInfo> getDao() {
		return this.userInfoDao;
	}

	@Autowired
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	@Override
	protected String getEntityName() {
		return "backstage/robot";
	}
	
	
	protected Page<UserInfo> getPage(Map<String, Object> params, HttpServletRequest req) {
		int pageNo = extractPageNo(req);
		int pageSize = extractPageSize(req);
		Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
		userInfoDao.queryRobots(params, page);
		return page;
	}
	
	protected Object doSave(HttpServletRequest req, HttpServletResponse resp, ModelAndView model) {
		Object view = null;
		try {
			UserInfo entity = extractEntity(req);
			
			verifyEntity(entity, model);

			boolean isSuccess = userInfoService.saveOrUpdate(entity);
			
			view = getSaveView(isSuccess, null, entity, model);
			
		} catch (Throwable throwable) {

			logger.error("failure to save in request[" + resolveParams(req) + "].caused by:" + throwable.getLocalizedMessage(), throwable);

			view = getSaveView(false, throwable, null, model);
		}
		return view;
	}
	
	
	@RequestMapping("downLoadExcelTemplate")
	public void downLoadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getSession().getServletContext().getRealPath(File.separator) + "temp" + File.separatorChar + 
                "机器人导入模板.xlsx";	
        XLSExportWrap.downloadXls(path,request, response,false);
	}
	
	@RequestMapping("toRobotDownLoadIframe")
	public String toRobotDownLoadIframe(HttpServletRequest request, HttpServletResponse response) throws IOException{
		return "bakcstage/robot/downLoad";
	}
	
	@RequestMapping("importExcel")
	@ResponseBody
	public Object importExcel(HttpServletRequest request, HttpServletResponse response) throws InvalidFormatException, IOException{
		String[] heads = {"账号","头像","等级"},filed = {"account","userPhoto","level"};
		Map<String,String> notNullFiledMap= new HashMap<String,String>();
		notNullFiledMap.put("account", "账号不能为空!");
		
		if(heads.length != filed.length){
			return Result.getServerError("系统配置错误");
		}
		
		if(request instanceof MultipartHttpServletRequest ){
			MultipartController multipart = new MultipartController();
			multipart.setDefaultBussinessDir(defaultBussinessDir);
			multipart.setImgSaveRoot(imgSaveRoot);
			multipart.setImgServerRoot(imgServerRoot);
			String ext = "|xls|xlsx|";
			boolean valid = multipart.isValidFile((MultipartHttpServletRequest)request, 2, ext);
			if(valid){
				String[] filePaths = null;
				try {
					filePaths = multipart.upload(request, "robot/importExcel/");
				} catch (Exception e) {
					return Result.getServerError("文件上传失败");
				}
				
				Workbook wb = null;
				InputStream input = null;
				try {
					File importExcel = new File(imgServerRoot+filePaths[0]);
					input = new FileInputStream(importExcel);
					wb = WorkbookFactory.create(input);
					Sheet sheet = wb.getSheetAt(0);
					
					int totalNum = sheet.getLastRowNum();
					
					if(totalNum<=0){
						return Result.getServerError("导入的数据不能为空");
					}
					
					if(totalNum>5000){
						return Result.getServerError("导入的数据不能大于5000条");
					}
					
					//验证标题
					Row headRow = sheet.getRow(0);
					if(headRow == null){
						return Result.getServerError("标题丢失");
					}
					
					Cell cell = null;
					for(int i=0,len=heads.length;i<len;i++){
						cell = headRow.getCell(i);
						System.out.println("cell:"+cell.getStringCellValue()+"==============");
						if( cell==null || !heads[i].equals(cell.getStringCellValue())){
							return Result.getServerError("丢失第"+(i+1)+"列标题:"+heads[i]);
						}
					}
					
					List<RobotXls> robotXlss = new ArrayList<RobotXls>(totalNum);
					RobotXls robot = null;
					StringBuilder errorMsg = new StringBuilder();
					Row dataRow = null;
					Cell cellData =null;
					Object value = null;
					for(int i=0,len=totalNum;i<len;i++){
						dataRow = sheet.getRow(i+1);
						robot = new RobotXls();
						for(int j=0,len2=filed.length;j<len2;j++){
							cellData = dataRow.getCell(j);
							if(cellData == null){
								if(notNullFiledMap.containsKey(filed[j])){
									errorMsg.append("第").append((i+2))
									.append("行,第").append((j+1)).append("列,")
									.append(notNullFiledMap.get(filed[j]))
									.append("\r\n");
									continue;
								}
							}
							value = cellData==null?null:getCellValue(cellData);
							BeanUtil.setProperty(robot, filed[j],value);
						}
						robotXlss.add(robot);
					}
					
					input.close();
					
					//验证的数据格式
					String noticeMsg = errorMsg.toString();
					if(StringUtils.isNotBlank(noticeMsg)){
						return Result.getServerError(noticeMsg);
					}
					
					userInfoService.batchUpdateRobot(robotXlss);
					return  Result.getSuccessForNoData();
				} catch (Exception e) {
					if(input != null){
						input.close();
					}
					return  Result.getServerError(e.getMessage());
				}
			    
			    //验证手机号是否存在
			    
			    
			    //importExcel.delete();
//			    String responseText = "";
//			    JSONObject json = new JSONObject();
//			    json.put("robots", robotXls);
//			    try {
//			    	responseText = HttpRequestUtil.post(batchImportRobotUrl, json.toString());
//			    	logger.debug(responseText);
//					JSONObject resJson = JSONObject.fromObject(responseText);
//					if (resJson.getInt("result_code") == 0) {
//						return  Result.getSuccessForNoData();
//					} else {
//						return Result.getServerError(resJson.getString("result_desc"));
//					}
//			    } catch (IOException e) {
//					return Result.getServerError(e.getLocalizedMessage());
//				}
			}else{
				return Result.getServerError("excel文件必须小于2M,扩展名为:\".xls\",\".xlsx\"");
			}
		}else{
			return Result.getServerError("文件不能为空");
		}
		
	}
	
	
	 @RequestMapping("exposeExcel")
	    public void exposeExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
	    	Map<String, Object> queryParams = extractQueryParams(request);
//	    	if(queryParams.containsKey("nickName")){
//	    		queryParams.put("nickName", CodingUtil.changeIso2Utf(queryParams.get("nickName").toString()));
//	    	}
	    	Page<RobotXls> page = new Page<RobotXls>(extractPageNo(request), extractPageSize(request));
	    	List<RobotXls> list = userInfoDao.queryRobotXls(queryParams, page);
	    	
	    	XLSExportWrap<RobotXls> ex = new XLSExportWrap<RobotXls>();
	    	
	    	String path = request.getSession().getServletContext().getRealPath(File.separator) + "temp" + File.separatorChar + 
	                "expose_" + System.currentTimeMillis() + ".xls";
	        File f = new File(path);
	        if(!f.exists()){
	        	f.getParentFile().mkdirs();
	        }
	        OutputStream os = new FileOutputStream(f);
	        ex.exportExcel("机器人导出数据表", new String[]{"账号", "头像", "等级"}, 
	        		new String[]{"account","userPhoto", "level"}, list, os);
	        os.flush();
	        os.close();
	        XLSExportWrap.downloadXls(path,request, response);
	    }
	    
	    
	    @SuppressWarnings("unused")
		private List<RobotXls> toExcelList(List<UserInfo> list){
	    	List<RobotXls> resList = new ArrayList<RobotXls>();
	    	RobotXls xls = null;
	    	UserLevel userLevel = null;	 
	    	for (UserInfo entity : list) {
	    		xls = new RobotXls();
	    		xls.setAccount(entity.getAccount());
	    		xls.setUserPhoto(entity.getUserPhoto());
	    		if(entity.getLevel() != null){
	    			userLevel = userLevelDao.get(entity.getLevel().longValue());
	    			if( userLevel != null){ 
	    				xls.setLevel(userLevel.getLevelName());
	    			}
	    		}
		   		
	    		resList.add(xls);
	    	}
	    	return resList;
	    }
	    
	    
	    private Object getCellValue(Cell cell) {  
	        Object ret;
	        String number=null;
	        long longNumber=0;
	        String replacedNumber=null;
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_BLANK:  
	            ret = "";  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            ret = cell.getBooleanCellValue();  
	            break;  
	        case Cell.CELL_TYPE_ERROR:  
	            ret = null;  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:  
	            if (DateUtil.isCellDateFormatted(cell)) {   
	            	ret = cell.getDateCellValue();  
	            } else {   
	            	number = NumberToTextConverter.toText(cell.getNumericCellValue());
	                
	                if(!number.contains(".")){
	                	replacedNumber = number.replace(".0", "");
	                	longNumber =  Long.parseLong(replacedNumber);
	                	if( longNumber > Integer.MAX_VALUE){
	                		ret = longNumber;
	                	}else{
	                		ret = Integer.parseInt(number.replace(".0", ""));
	                	}
	                	
	                }else{
	                	ret = Double.parseDouble(number);
	                }
	            }  
	            break;  
	        case Cell.CELL_TYPE_STRING:  
	            ret = cell.getRichStringCellValue().getString();  
	            break;  
	        default:  
	            ret = null;  
	        }  
	          
	        return ret; //有必要自行trim  
	    }  
	    
	   
//	    private StringBuilder beachCheckAccountIsExist(RobotXls[] robotXls){
//	    	
//	    }
	    
	    public static void main(String[] args) {
			System.out.println(Pattern.matches("^[a-zA-Z0-9]{6,12}$", "q3w12#1"));
			System.out.println("账号".equals("账号"));
		}
}

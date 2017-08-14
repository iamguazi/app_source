package com.ffcs.icity.mvc.asyn.constant;
/**
 * 基础常量类
 *
 */
public class BaseConstant {
    
    public static String RESULT = "result";
    
    public static String STATUS = "status";
    public static String DESC = "desc";
    public static String DATA = "data";
    public static String TIMESTAMP = "timestamp";
    public static String PAGE = "page";
    public static String ENTITY = "entity";
	
	public static final int SUCCESS_FOR_DATA = 10000;
	public static final String SUCCESS_FOR_DATA_DESC = "成功并返回数据";
	
	
	public static final int SUCCESS_FOR_NODATA = 10001;
	public static final String SUCCESS_FOR_NODATA_DESC = "成功但无数据";

	
	public static final int PARAM_ERROR = 10002;
	public static final String PARAM_ERROR_DESC = "参数错误";

	
	public static final int SERVICE_EXCEPTION = 10003;
	public static final String SERVICE_EXCEPTION_DESC = "服务器异常";
	
	public static final int SERVER_EXCEPTION = 10004;//业务错误

}

package com.ffcs.icity.api.module.IM.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.baseModule.NoValidController;
import com.ffcs.icity.api.core.exception.ApiException;
import com.ffcs.icity.api.core.exception.InvalidRequestArgumentException;
import com.ffcs.icity.api.module.IM.comm.Constants;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobIMUsers;

import static com.ffcs.icity.api.util.RequestArgumentAssert.assertNotEmpty;
public class CreateIMUserController extends NoValidController{
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateIMUserController.class);
	private static final String APPKEY = Constants.APPKEY;
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

 
	
	@Override
	public void verifyBusiArgument(Map<String, Object> requestArgument)
			throws InvalidRequestArgumentException {
		// TODO Auto-generated method stub
		assertNotEmpty(requestArgument, "prefix");
		assertNotEmpty(requestArgument, "username_list");
		
	}


	@Override
	public Object handleRequest(Map<String, Object> requestArgument)
			throws ApiException {
		// TODO Auto-generated method stub
		
		String usernameList=requestArgument.get("username_list").toString();
		String usernamePrefix=requestArgument.get("prefix").toString();
		
		ObjectNode objectNode = factory.objectNode();
		ArrayNode genericArrayNode = genericArrayNode(usernameList, usernamePrefix);
		objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
		if (null != objectNode) {
            LOGGER.info("注册IM用户[批量]: " + objectNode.toString());
        }
		return "ok";
	}
	public static void main(String[] args) {
//		String usernameList="1,2,10,1014,1015,1016,1017,1018,1019,1020,1021";
//		String usernamePrefix="s";
//		
//		ObjectNode objectNode = factory.objectNode();
//		ArrayNode genericArrayNode = genericArrayNode(usernameList, usernamePrefix);
//		objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
//		if (null != objectNode) {
//            LOGGER.info("注册IM用户[批量]: " + objectNode.toString());
//        }
	}
	
	private  ArrayNode genericArrayNode(String usernameList,String usernamePrefix) {
		ArrayNode arrayNode = factory.arrayNode();
		 
		for (int i = 0; i < usernameList.split(",").length; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + usernameList.split(",")[i]);
			userNode.put("password", Constants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}
		return arrayNode;
	}
}

package com.ffcs.icity.api.module.IM.service;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ffcs.icity.api.module.IM.httpclient.apidemo.EasemobIMUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateNewIMUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateNewIMUserService.class);

    public void createIMUser(String username, String password, String nickname) {
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", username);
        datanode.put("password", password);
        datanode.put("nickname", nickname);


        ObjectNode createNewIMUserSingleNode = new EasemobIMUsers().createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
            LOGGER.info("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
        } else {
            LOGGER.info("注册IM用户报错");
        }

    }

    public static void main(String[] args) {
        for (int i = 1914; i < 2499; i++) {
            new CreateNewIMUserService().createIMUser("u_" + i + "0", "123456", null);
        }

//		EasemobIMUsers server=new EasemobIMUsers();
//		server.createNewIMUserBatchGen("s", 10L, 10L);
//		System.out.println(server.getIMUsersByUserName("s2"));
    }
}

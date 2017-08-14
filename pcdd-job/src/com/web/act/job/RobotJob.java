package com.web.act.job;


import com.web.act.dao.OtherParamsDao;
import com.web.act.dao.RoomInfoDao;
import com.web.act.utils.Config;
import com.web.act.utils.UrlUtil;
import com.web.act.vo.OtherParams;
import com.web.act.vo.RoomInfo;
import com.web.act.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @version v 1.0
 * @author: 黄嘉彬
 * @date: 2014-12-15
 */


@Service("RobotJob")
public class RobotJob {
    @Autowired(required = true)
    private RoomInfoDao roomInfoDao;

    @Autowired
    private OtherParamsDao otherParamsDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotJob.class);

    public void run() {
        LOGGER.warn("=====================定时取机器人开始===========================");

        OtherParams otherParams = new OtherParams();
        otherParams.setParamsGroup("robot");
        List<OtherParams> pointList = otherParamsDao.findByCondition(otherParams);
        String chujiStr = "";
        String zhongjiStr = "";
        String gaojiStr = "";

        for (OtherParams params : pointList) {
            if ("chuji_robot".equals(params.getParamsKey())) {
                chujiStr = params.getParamsValue();
            }

            if ("zhongji_robot".equals(params.getParamsKey())) {
                zhongjiStr = params.getParamsValue();
            }

            if ("gaoji_robot".equals(params.getParamsKey())) {
                gaojiStr = params.getParamsValue();
            }
        }

        List<RoomInfo> list = roomInfoDao.findByCondition(null);
        List<UserInfo> userList = new ArrayList<UserInfo>();
        int peopleCount = 0;
        for (RoomInfo room : list) {
            //这里获取机器人最大数量
            peopleCount = peopleCount + room.getRebotMaxCount();
            for (int i = 0; i < room.getRebotMaxCount(); i++) {
                if (room.getRebotMaxCount() > 300) {
                    room.setRebotMaxCount(300);
                }
                UserInfo user = new UserInfo();
                //总共2400个机器人用户   编号100到200分到第一个房间  以此类推到其他24个
                user.setId(getRandomNum(room.getId() * 100, room.getId() * 100 + 100));
                user.setAreaId(room.getAreaId());
                user.setRoomId(room.getId());
                if ((room.getAreaId() == 1 || room.getAreaId() == 4) && !"".equals(chujiStr)) {
                    String[] str = chujiStr.replaceAll("，", ",").split(",");
                    int length = str.length;
                    user.setPoint(Integer.parseInt(str[getRandomNum(0, length)]));
                } else if ((room.getAreaId() == 2 || room.getAreaId() == 5) && !"".equals(zhongjiStr)) {
                    String[] str = zhongjiStr.replaceAll("，", ",").split(",");
                    int length = str.length;
                    user.setPoint(Integer.parseInt(str[getRandomNum(0, length)]));
                } else if ((room.getAreaId() == 3 || room.getAreaId() == 6) && !"".equals(gaojiStr)) {
                    String[] str = gaojiStr.replaceAll("，", ",").split(",");
                    int length = str.length;
                    user.setPoint(Integer.parseInt(str[getRandomNum(0, length)]));
                } else {
                    user.setPoint(getRandomNum(room.getPerMinPoint().intValue(), room.getPerMinPoint().intValue() + 100));
                }
                userList.add(user);
            }
        }
        System.out.println("=================peopleCount" + peopleCount + "=================");
        //随机排列
        Collections.shuffle(userList);
        for (UserInfo userInfo : userList) {

//            int randomNum = getRandomNum(1, 10);
            //90%投大小单双
            int biliId = 0;
            int randomNum = getRandomNum(1, 10);
            boolean isNumber = false;
            if (randomNum >= 2) {
                //投注极大极小的概率降低
                biliId = getRandomNum(1, 10);
                if (biliId == 5) {
                    if (getRandomNum(1, 10) > 3) {
                        biliId++;
                    }
                }
                if (biliId == 10) {
                    if (getRandomNum(1, 10) > 3) {
                        biliId = 9;
                    }
                }
            } else {
                //30%投数字
                biliId = getRandomNum(11, 38);
                isNumber = true;
            }
            if (isNumber && userInfo.getPoint() > 2000) {
                userInfo.setPoint(2000);
            }

            biliId = biliId + (userInfo.getAreaId() - 1) * 42;
            UrlUtil.sendGet(Config.yuming + "pcdd-api-client-interface/rebot/open?user_id=" + userInfo.getId()
                    + "&area_id=" + userInfo.getAreaId()
                    + "&room_id=" + userInfo.getRoomId()
                    + "&point=" + userInfo.getPoint()//机器人投注的金额范围
                    + "&bili_id=" + biliId);//这里设置机器人投注的选项范围
//			 try {
//                 //随机间隔时间
//                 Thread.sleep(getRandomNum((120 * 100 / (peopleCount * 30)), (120 * 1000 / peopleCount)));
//             } catch (InterruptedException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
        }


    }

    public static int getRandomNum(int min, int max) {

        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            long t1 = System.currentTimeMillis();
            UrlUtil.sendGet(Config.yuming + "pcdd-api-client-interface/user/login");
            System.out.println((System.currentTimeMillis() - t1) + "ms");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

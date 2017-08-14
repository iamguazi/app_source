package com.ffcs.icity.mvc.backstage.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ffcs.icity.mvc.backstage.user.entity.RobotXls;
import com.ffcs.icity.mvc.backstage.user.entity.UserInfo;
import com.ffcs.icity.mvc.dao.CRUDDao;
import com.ffcs.icity.mybatis.MyBatisRepository;
import com.ffcs.icity.mybatis.Page;

@MyBatisRepository
public interface UserInfoDao extends CRUDDao<UserInfo> {
	List<UserInfo> queryRobots(Map<String,Object> params , Page<UserInfo> page);
	/**
	 * 获取要导出的机器人数据
	 * @param params
	 * @param page
	 * @return
	 */
	List<RobotXls> queryRobotXls(Map<String,Object> params , Page<RobotXls> page);
	/**
	 * 批量导入要修改的机器人数据
	 * @param robotXlss
	 * @return
	 */
	int batchUpdateRobotXls(List<RobotXls> robotXlss);
	int updatePoint(Map<String,Object> params);
	int updatePointAndAllPoint(Map<String,Object> params);
	void updateStatus(Map<String,Object> params);
	Integer isAccountExits(@Param("account") String account, @Param("id") Long id);
	List<String> beachCheckAccountIsExist();
	void resetPassword(@Param("id") Long id,@Param("password")String password);
	List<UserInfo> queryUserBankInfos(Map<String,Object> params, Page<UserInfo> page);
	int updateUserBankInfos(UserInfo userUserInfo);
	int batchCleanUserBankInfos(Long[] ids);
	
	@Select(" select count(*) from USER_INFO where CODE = #{code} and ID = #{id} ")
	int countUserChild(@Param("code")int code, @Param("id")int id);
	
	@Update(" update USER_INFO SET CODE = #{parentId} where id = #{id} ")
	int updateCodeById(@Param("id") int id, @Param("parentId")int parentId);
	
	
	
}
package manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ffcs.icity.mvc.backstage.manager.dao.BackstageMenuDao;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-*.xml"})  
@ActiveProfiles("development")
public class TestBackstageMenu {
	/***
	 * 该类必须有一个@Test  不然打包 报错
	 */
	
	@Autowired
	private BackstageMenuDao backstageMenuDao;
	
//	@Test
	public void testGetPermissions(){
		backstageMenuDao.getPermissions(11l);
	}
	
//	@Test
	public  void testNull(){
		
	}
	
}

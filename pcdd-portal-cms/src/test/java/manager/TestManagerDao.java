package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.ffcs.icity.mvc.backstage.manager.entity.Manager;
import com.ffcs.icity.mvc.backstage.manager.service.ManagerService;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-*.xml"})  
@ActiveProfiles("development")
public class TestManagerDao {
	/***
	 * 该类必须有一个@Test  不然打包 报错
	 */
	@Autowired
    private ManagerService managerService;
	
//	@Test
	public void testManagerSave() throws Exception{
		Manager manager = new Manager();
		manager.setName("test2");
		manager.setPasswd("123456");
		manager.setType("normal");
		manager.setStatus(0);
		managerService.saveService(manager,"1");
	}
	
//	@Test
	public void testManagerDelete() throws Exception{
		Long[] ids = {34l};
		managerService.deleteService(ids);
	}
	
	
//	@Test
	public  void testNull(){
		
	}
}

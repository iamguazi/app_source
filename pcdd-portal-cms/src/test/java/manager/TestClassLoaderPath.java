package manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestClassLoaderPath {
	
	@Test
	public void test(){
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		a.add("3");
		
		for (String temp : a) {
			if("0".equals(temp)){
				a.remove(temp);
			}
		}
	}
}

package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ajdbxt.service.Process.ProcessInfoService;
import com.ajdbxt.service.User.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class processTest {
@Resource
//private ProcessInfoService processInfoService ;

private UserService userService;

//public void setProcessInfoService(ProcessInfoService processInfoService) {
//	this.processInfoService = processInfoService;
//}






public void setUserService(UserService userService) {
	this.userService = userService;
}






@Test
public void t(){
    System.out.println("a");
//	processInfoService.getInfoList(1, "001");
	
}
}
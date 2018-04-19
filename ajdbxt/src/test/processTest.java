package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ajdbxt.service.Process.ProcessInfoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class processTest {
@Resource
private ProcessInfoService processInfoService ;



public void setProcessInfoService(ProcessInfoService processInfoService) {
	this.processInfoService = processInfoService;
}



//
//@Test
//public void s(){
//	page_list_HandleInformationVO page_list_HandleInformation=new page_list_HandleInformationVO();
//	page_list_HandleInformation.setHandle_administrativeCase("1");
//	handleService.VO_HandleInformation_By_PageAndSearch(page_list_HandleInformation);
//	
//}


@Test
public void t(){
    
//	processInfoService.getInfoList(1, "001");
	
}
}
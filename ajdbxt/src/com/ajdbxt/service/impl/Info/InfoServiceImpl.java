package com.ajdbxt.service.impl.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.ajdbxt.dao.Info.InfoDao;
import com.ajdbxt.dao.Info.InfoPoliceDao;
import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;

import util.JsonUtils;
import util.Tel;

public class InfoServiceImpl implements InfoService {
	private InfoDao infoDao;
	private ProcessDao processDao;
	public void setInfoPoliceDao(InfoPoliceDao infoPoliceDao) {
		this.infoPoliceDao = infoPoliceDao;
	}
	private InfoPoliceDao infoPoliceDao;
	private List<Tel> tel ;
	private String[] params;
	public InfoPoliceDao getInfoPoliceDao() {
		return infoPoliceDao;
	}

	public ProcessDao getProcessDao() {
		return processDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	public InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public InfoDao getCaseInfoDao() {
		return infoDao;
	}

	public void setCaseInfoDao(InfoDao caseInfoDao) {
		this.infoDao = caseInfoDao;
	}
	
	
	@Override
	public String saveCase(ajdbxt_info caseInfo) {
		caseInfo.setAjdbxt_info_id(UUID.randomUUID().toString());
		caseInfo.setInfo_gmt_ceate(util.Time.getStringSecond());
		caseInfo.setInfo_gmt_modify(caseInfo.getInfo_gmt_ceate());//保存时将修改时间设为创建时间
		oneceRank(caseInfo);
		//哲理要写排班逻辑
		processDao.saveProcessByCaseId(caseInfo.getAjdbxt_info_id());		
		infoDao.saveCase(caseInfo);
		return JsonUtils.toJson(caseInfo);
	}
	private void oneceRank(ajdbxt_info caseInfo) {//排班主协办人员
		List<ajdbxt_police> polices=infoPoliceDao.findPoliceByDepartment(caseInfo.getInfo_department());
		String police_id=null;
		
		//得到所队长id方便分配
		for(ajdbxt_police police : polices) {
			if(police.getPolice_duty().equals("所长")||police.getPolice_duty().equals("大队长")) {
				police_id=police.getAjdbxt_police_id();
				polices.remove(police);//取得后移除，因为不在考虑范围
				break;
			}
		}
		//如果所队长没有负责过，则分配
		if(new Random().nextBoolean()&&infoDao.isCaptainWorked(police_id)) {
			caseInfo.setInfo_main_police(police_id);
		}
		//的到副所队长和普通警员的执勤次数
		int countCap=0;
		int countNom=0;
		List<ajdbxt_police> cap=new ArrayList();
		List<ajdbxt_police> nom=new ArrayList();
		for(ajdbxt_police police :polices) {
			int temp=infoDao.countProcessByPoliceId(police.getAjdbxt_police_id());
			if(police.getPolice_duty().equals("警员")) {
				countNom+=temp;
				nom.add(police);
			}else if(police.getPolice_duty().equals("副队长")||police.getPolice_duty().equals("副所长")){
				countCap+=temp;
				cap.add(police);
			}
		}
		//如副所队长没执勤过
		if(countCap==0) {
			ajdbxt_police police=cap.get(new Random().nextInt(cap.size()));
			if(caseInfo.getInfo_main_police()==null||caseInfo.getInfo_main_police().isEmpty()) {
				caseInfo.setInfo_main_police(police.getAjdbxt_police_id());
			}else {
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}
		}
		
		if(countNom/countCap>=3) {
			ajdbxt_police police=cap.get(new Random().nextInt(cap.size()));
			if(caseInfo.getInfo_main_police()==null||caseInfo.getInfo_main_police().isEmpty()) {
				caseInfo.setInfo_main_police(police.getAjdbxt_police_id());
			}else {
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}
			countCap++;
		}else {
			ajdbxt_police police=nom.get(new Random().nextInt(nom.size()));
			if(caseInfo.getInfo_main_police()==null||caseInfo.getInfo_main_police().isEmpty()) {
				caseInfo.setInfo_main_police(police.getAjdbxt_police_id());
			}else {
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}
			countNom++;
		}
		if(caseInfo.getInfo_assistant_police_one()==null||caseInfo.getInfo_assistant_police_one().isEmpty()) {//如果第一协办为设定则设定
			if(countNom/countCap>=3) {
				boolean isDouble=true;
				while(isDouble) {
					ajdbxt_police police=cap.get(new Random().nextInt(cap.size()));
					if(!caseInfo.getInfo_main_police().equals(police.getAjdbxt_police_id())) {
						isDouble=false;
						caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
						countCap++;
					}
				}
			}else {
				boolean isDouble=true;
				while(isDouble) {
					ajdbxt_police police=cap.get(new Random().nextInt(cap.size()));
					if(!caseInfo.getInfo_main_police().equals(police.getAjdbxt_police_id())) {
						isDouble=false;
						caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
						countCap++;
					}
				}
			}
		}
	}
	@Override
	public String twoceRank(ajdbxt_info caseInfo) {//添加第二个协办人员时调用
		List<ajdbxt_police> polices=infoPoliceDao.findPoliceByDepartment(caseInfo.getInfo_department());
		String police_id=null;
		
		//得到所队长id方便分配
		for(ajdbxt_police police : polices) {
			if(police.getPolice_duty().equals("所长")||police.getPolice_duty().equals("大队长")) {
				police_id=police.getAjdbxt_police_id();
				polices.remove(police);//取得后移除，因为不在考虑范围
				break;
			}
		}
		
		int countCap=0;
		int countNom=0;
		List<ajdbxt_police> cap=new ArrayList();
		List<ajdbxt_police> nom=new ArrayList();
		for(ajdbxt_police police :polices) {
			int temp=infoDao.countProcessByPoliceId(police.getAjdbxt_police_id());
			if(police.getPolice_duty().equals("警员")) {
				countNom+=temp;
				nom.add(police);
			}else if(police.getPolice_duty().equals("副队长")||police.getPolice_duty().equals("副所长")){
				countCap+=temp;
				cap.add(police);
			}
		}
		if(caseInfo.getInfo_assistant_police_two()==null||caseInfo.getInfo_assistant_police_two().isEmpty()) {//如果第一协办为设定则设定
			if(countNom/countCap>=3) {
				boolean isDouble=true;
				while(isDouble) {
					ajdbxt_police police=cap.get(new Random().nextInt(cap.size()));
					if(!caseInfo.getInfo_main_police().equals(police.getAjdbxt_police_id())&&!caseInfo.getInfo_assistant_police_two().equals(police.getAjdbxt_police_id())) {
						isDouble=false;
						caseInfo.setInfo_assistant_police_two(police.getAjdbxt_police_id());
						countCap++;
					}
				}
			}else {
				boolean isDouble=true;
				while(isDouble) {
					ajdbxt_police police=cap.get(new Random().nextInt(cap.size()));
					if(!caseInfo.getInfo_main_police().equals(police.getAjdbxt_police_id())&&!caseInfo.getInfo_assistant_police_two().equals(police.getAjdbxt_police_id())) {
						isDouble=false;
						caseInfo.setInfo_assistant_police_two(police.getAjdbxt_police_id());
						countCap++;
					}
				}
			}
		}
		infoDao.saveCase(caseInfo);
		return JsonUtils.toJson(caseInfo);
	}
	
	@Override
	public void updateCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		caseInfo.setInfo_gmt_modify(util.Time.getStringSecond());
		infoDao.updateCase(caseInfo);
		
	}

	@Override
	public void deleteCase(String caseInfo_id) {
		// TODO Auto-generated method stub
		infoDao.deleteCase(caseInfo_id);
	}

//	@Override
//	public Page_list_caseInfoVo showCaseList(Page_list_caseInfoVo page_list_caseInfoVo) {
//		List<ajdbxt_info> listCase=new ArrayList<ajdbxt_info>();
//		int totalRecords=caseInfoDao.getTotalCase(page_list_caseInfoVo);
//		Page_list_caseInfoVo caseInfoVo=new Page_list_caseInfoVo();
//		caseInfoVo.setCurrPage(page_list_caseInfoVo.getCurrPage());
//		caseInfoVo.setPageSize(page_list_caseInfoVo.getPageSize());
//		caseInfoVo.setCountRecords(totalRecords);
//		
//		caseInfoVo.setTotalPages((int)Math.ceil((totalRecords)/(page_list_caseInfoVo.getPageSize())));
//		if(page_list_caseInfoVo.getCurrPage()<=1) {
//			page_list_caseInfoVo.setHavePrePage(false);
//		}else {
//			page_list_caseInfoVo.setHavePrePage(true);
//		}
//		
//		if(page_list_caseInfoVo.getCurrPage()>=page_list_caseInfoVo.getTotalPages()) {
//			page_list_caseInfoVo.setHaveNexPage(false);
//		}else {
//			page_list_caseInfoVo.setHaveNexPage(true);
//		}
//		
//		listCase=caseInfoDao.getCaseByPage(page_list_caseInfoVo);
//		page_list_caseInfoVo.setCaselist(listCase);
//		
//		return page_list_caseInfoVo;
//	}

	@Override
	public ajdbxt_info findOneCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		return infoDao.findOneCase(caseInfo);
	}

	@Override
	public Page_list_caseInfoVo showCaseList(Page_list_caseInfoVo page_list_caseInfoVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllCase(Page_list_caseInfoVo infoVO) {
		List<ajdbxt_info> list=infoDao.findSomeCase(infoVO.getCurrPage()*10, infoVO.getPageSize());
		infoVO.setCaselist(list);
		infoVO.setCountRecords(infoDao.countAllCase());
		int pages=infoVO.getCountRecords()/infoVO.getPageSize();
		if(infoVO.getCountRecords()/infoVO.getPageSize()>0) {
			pages++;
		}
		infoVO.setTotalPages(pages);
		return JsonUtils.toJson(infoVO);
	}
	private ajdbxt_police rankWork(String apartment) {
		
		return null;
	}

	@Override
	public void save(ajdbxt_info caseInfo) {
		infoDao.saveCase(caseInfo);
		ajdbxt_police police;
		police=infoPoliceDao.findPoliceById(caseInfo.getInfo_main_police());
		tel=new ArrayList<Tel>();
		tel.add(new Tel(police.getPolice_phone_number(),"86"));
		
		police=infoPoliceDao.findPoliceById(caseInfo.getInfo_assistant_police_one());
		tel.add(new Tel(police.getPolice_phone_number(),"86"));
		if(caseInfo.getInfo_assistant_police_two()!=null||!caseInfo.getInfo_assistant_police_two().isEmpty()) {
			
		}
	}

}

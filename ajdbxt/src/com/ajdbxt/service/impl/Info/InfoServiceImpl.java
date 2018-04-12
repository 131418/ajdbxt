package com.ajdbxt.service.impl.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.ajdbxt.dao.Info.InfoDao;
import com.ajdbxt.dao.Info.InfoDepartmentDao;
import com.ajdbxt.dao.Info.InfoPoliceDao;
import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Process.ProcessInfoDTO;
import com.ajdbxt.domain.VO.Info.LegalSystemAndLeadersVO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;

import util.JsonUtils;
import util.Tel;

public class InfoServiceImpl implements InfoService {
	private InfoDao infoDao;
	private ProcessDao processDao;
	private InfoDepartmentDao infoDepartmentDao;
	private InfoPoliceDao infoPoliceDao;
	private List<Tel> tel ;
	private String[] params;
	public InfoDepartmentDao getInfoDepartmentDao() {
		return infoDepartmentDao;
	}
	public void setInfoDepartmentDao(InfoDepartmentDao infoDepartmentDao) {
		this.infoDepartmentDao = infoDepartmentDao;
	}

	public void setInfoPoliceDao(InfoPoliceDao infoPoliceDao) {
		this.infoPoliceDao = infoPoliceDao;
	}
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
			System.out.println("it is working");
			ajdbxt_police police=cap.get(new Random().nextInt(cap.size()-1));
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
		caseInfo.setInfo_gmt_modify(util.Time.getStringSecond());
		infoDao.updateCase(caseInfo);
	}

	@Override
	public void deleteCase(String caseInfo_id) {
		infoDao.deleteCase(caseInfo_id);
	}

	@Override
	public String getAllCase(Page_list_caseInfoVo infoVO) {
		List<ProcessInfoDTO> case_list=new ArrayList<ProcessInfoDTO>();
		List<ajdbxt_info> list=infoDao.findSomeCase((infoVO.getCurrPage()-1)*infoVO.getPageSize(), infoVO.getPageSize());
		ProcessInfoDTO processInfo;
		List<ajdbxt_police> policeList;
		for(ajdbxt_info info:list) {
			processInfo=new ProcessInfoDTO();
			processInfo.setInfo(info);
			processInfo.setDepartment(infoDepartmentDao.findDepartmentById(info.getInfo_department()));
			policeList=new ArrayList<ajdbxt_police>();
			policeList.add(infoPoliceDao.findPoliceById(info.getInfo_main_police()));
			policeList.add(infoPoliceDao.findPoliceById(info.getInfo_assistant_police_one()));
			String three=info.getInfo_assistant_police_two();
			if(three!=null&&three.isEmpty()==false) {
				policeList.add(infoPoliceDao.findPoliceById(three));
			}
			processInfo.setPolice(policeList);
			case_list.add(processInfo);
		}
		infoVO.setCaselist(case_list);
		infoVO.setCountRecords(infoDao.countAllCase());
		int pages=infoVO.getCountRecords()/infoVO.getPageSize();
		if(infoVO.getCountRecords()%infoVO.getPageSize()>0) {
			pages++;
		}
		infoVO.setTotalPages(pages);
		return JsonUtils.toJson(infoVO);
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

	@Override
	public String getLegalsAndLeadersAndDepartment() {
		LegalSystemAndLeadersVO lalVO=new LegalSystemAndLeadersVO();
		lalVO.setLegals(infoPoliceDao.findLegals());
		lalVO.setLeaders(infoPoliceDao.findLeaders());
		lalVO.setDepartments(infoDepartmentDao.findAllDepartment());
		return JsonUtils.toJson(lalVO);
	}

	@Override
	public ProcessInfoDTO getSingleInfo(String info_id) {
		System.out.println("进来了"+info_id);
		ajdbxt_info info=infoDao.findCaseById(info_id);
		System.out.println("案件信息"+info.toString());
		ajdbxt_department department=infoDepartmentDao.findDepartmentById(info.getInfo_department());
		System.out.println("部门信息"+department.toString());
		ProcessInfoDTO processInfoDTO=new ProcessInfoDTO();
		processInfoDTO.setInfo(info);
		processInfoDTO.setDepartment(department);
		List<ajdbxt_police> policeList=new ArrayList<ajdbxt_police>();
		policeList.add(infoPoliceDao.findPoliceById(info.getInfo_main_police()));
		policeList.add(infoPoliceDao.findPoliceById(info.getInfo_assistant_police_one()));
		String three=info.getInfo_assistant_police_two();
		if(three!=null&&three.isEmpty()==false) {
			policeList.add(infoPoliceDao.findPoliceById(three));
		}
		processInfoDTO.setPolice(policeList);
		return processInfoDTO;
	}
	@Override
	public String getPolices(String info_department) {
		List list=infoPoliceDao.findPoliceByDepartment(info_department);
		return JsonUtils.toJson(list);
	}
	
}

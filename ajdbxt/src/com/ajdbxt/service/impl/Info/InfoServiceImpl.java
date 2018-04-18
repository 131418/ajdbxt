package com.ajdbxt.service.impl.Info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map.Entry;
import com.ajdbxt.dao.Info.InfoDao;
import com.ajdbxt.dao.Info.InfoDepartmentDao;
import com.ajdbxt.dao.Info.InfoPoliceDao;
import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.dao.Process.ProcessPoliceDao;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.DTO.Process.ProcessInfoDTO;
import com.ajdbxt.domain.VO.Info.LegalSystemAndLeadersVO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;
import com.ajdbxt.domain.DO.*;
import util.JsonUtils;
import util.MsgSend;
import util.SMSThread;
import util.Tel;

public class InfoServiceImpl implements InfoService {
	private InfoDao infoDao;
	private ProcessDao processDao;
	private InfoDepartmentDao infoDepartmentDao;
	private InfoPoliceDao infoPoliceDao;
	
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
		ProcessDTO processDTO=new ProcessDTO();//回传dto而不是Info
		oneceRank(caseInfo);//下面要得到警察写逻辑
		//哲理要写排班逻辑
		processDTO.setInfo(caseInfo);
		List<ajdbxt_police> polices=new ArrayList<>();
		polices.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_main_police()));
		polices.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_assistant_police_one()));
		processDTO.setPolice(polices);
		processDTO.setDepartment(infoDepartmentDao.findDepartmentById(caseInfo.getInfo_department()));
		processDTO.setCap(infoPoliceDao.findCaptainByDepartment(caseInfo.getInfo_department()));
		return JsonUtils.toJson(processDTO);
	}
	private void oneceRank(ajdbxt_info caseInfo) {//排班主协办人员
		List<ajdbxt_police> polices=infoPoliceDao.findPoliceByDepartment(caseInfo.getInfo_department());
		int countCap=0;
		int countNom=0;
		ajdbxt_police chief=null;
		Map<ajdbxt_police,Integer> capM=new HashMap<ajdbxt_police,Integer>();
		Map<ajdbxt_police,Integer> nomM=new HashMap<ajdbxt_police,Integer>();
		List<ajdbxt_police> cap=new ArrayList();
		List<ajdbxt_police> nom=new ArrayList();
		//得到所队长id方便分配
		for(ajdbxt_police police : polices) {
			if(police.getPolice_duty().equals("所长")||police.getPolice_duty().equals("大队长")) {
				chief=police;
				polices.remove(police);//取得后移除，因为不在考虑范围
				break;
			}
		}
		//如果所队长没有负责过，则分配
		if(new Random().nextBoolean()&&infoDao.isCaptainWorked(chief.getAjdbxt_police_id())) {
			caseInfo.setInfo_main_police(chief.getAjdbxt_police_id());
		}
		//的到副所队长和普通警员的执勤次数
		for(ajdbxt_police police :polices) {
			int temp=infoDao.countProcessByPoliceId(police.getAjdbxt_police_id());
			if(police.getPolice_duty().contains("警员")) {
				countNom+=temp;
				nom.add(police);
				nomM.put(police, temp);
			}else if(police.getPolice_duty().contains("副队长")||police.getPolice_duty().contains("副所长")){
				countCap+=temp;
				cap.add(police);
				capM.put(police, temp);
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
			countCap++;
			cap.remove(police);
		}
		Comparator<Map.Entry<ajdbxt_police, Integer>> sort=new Comparator<Map.Entry<ajdbxt_police,Integer>>() {//map排序器降
			@Override
			public int compare(Entry<ajdbxt_police, Integer> o1, Entry<ajdbxt_police, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		};
		List<Map.Entry<ajdbxt_police, Integer>> capList=new ArrayList<Map.Entry<ajdbxt_police,Integer>>(capM.entrySet());
		List<Map.Entry<ajdbxt_police, Integer>> nomList=new ArrayList<Map.Entry<ajdbxt_police,Integer>>(nomM.entrySet());
		if(capList.size()>0) {
			Collections.sort(capList, sort);
		}
		if(nomList.size()>0) {
			Collections.sort(nomList, sort);
		}
		if((countNom/countCap>=3&&capList.size()>0)||nomList.size()<=0) {
			ajdbxt_police police=capList.get(0).getKey();
			if(caseInfo.getInfo_main_police()==null||caseInfo.getInfo_main_police().isEmpty()) {
				caseInfo.setInfo_main_police(police.getAjdbxt_police_id());
			}else {
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}
			capList.remove(0);
			countCap++;
		}else if(capList.size()<=0||(countNom/countCap<3&&nomList.size()>0)){
			ajdbxt_police police=nomList.get(0).getKey();
			if(caseInfo.getInfo_main_police()==null||caseInfo.getInfo_main_police().isEmpty()) {
				caseInfo.setInfo_main_police(police.getAjdbxt_police_id());
			}else {
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}
			nomList.remove(0);
			countNom++;
		}else {
			caseInfo.setInfo_main_police(chief.getAjdbxt_police_id());//如果该派出所只有一个所队长才会到这里
			return;
		}
		if(caseInfo.getInfo_assistant_police_one()==null||caseInfo.getInfo_assistant_police_one().isEmpty()) {//如果第一协办为设定则设定
			if((countNom/countCap>=3&&capList.size()>0)||nomList.size()<=0) {
				ajdbxt_police police=capList.get(0).getKey();
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}else if(capList.size()<=0||(countNom/countCap<3&&nomList.size()>0)){
				ajdbxt_police police=nomList.get(0).getKey();
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}
		}
	}
	@Override
	public String twoceRank(ajdbxt_info caseInfo) {//添加第二个协办人员时调用
		List<ajdbxt_police> polices=infoPoliceDao.findPoliceByDepartment(caseInfo.getInfo_department());
		int countCap=1;
		int countNom=0;
		ajdbxt_police chief=null;
		List<ajdbxt_police> cap=new ArrayList();
		List<ajdbxt_police> nom=new ArrayList();
		Map<ajdbxt_police,Integer> capM=new HashMap<ajdbxt_police,Integer>();
		Map<ajdbxt_police,Integer> nomM=new HashMap<ajdbxt_police,Integer>();
		for(ajdbxt_police police : polices) {
			if(police.getPolice_duty().equals("所长")||police.getPolice_duty().equals("大队长")) {
				chief=police;
				polices.remove(police);//取得后移除，因为不在考虑范围
				break;
			}
		}
		for(ajdbxt_police police :polices) {
			int temp=infoDao.countProcessByPoliceId(police.getAjdbxt_police_id());
			if(police.getPolice_duty().contains("警员")&&
					(police.getAjdbxt_police_id().equals(caseInfo.getInfo_main_police())==false)&&
						(police.getAjdbxt_police_id().equals(caseInfo.getInfo_assistant_police_one())==false)) {
				countNom+=temp;
				nom.add(police);
				nomM.put(police, temp);
			}else if((police.getPolice_duty().contains("副队长")||police.getPolice_duty().contains("副所长"))
					&&(police.getAjdbxt_police_id().equals(caseInfo.getInfo_main_police())==false)
						&&(police.getAjdbxt_police_id().equals(caseInfo.getInfo_assistant_police_one())==false)){
				countCap+=temp;
				cap.add(police);
				capM.put(police, temp);
			}
		}
		Comparator<Map.Entry<ajdbxt_police, Integer>> sort=new Comparator<Map.Entry<ajdbxt_police,Integer>>() {//map排序器降
			@Override
			public int compare(Entry<ajdbxt_police, Integer> o1, Entry<ajdbxt_police, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		};
		List<Map.Entry<ajdbxt_police, Integer>> capList=new ArrayList<Map.Entry<ajdbxt_police,Integer>>(capM.entrySet());
		List<Map.Entry<ajdbxt_police, Integer>> nomList=new ArrayList<Map.Entry<ajdbxt_police,Integer>>(nomM.entrySet());
		if(capList.size()>0) {
			Collections.sort(capList, sort);
		}
		if(nomList.size()>0) {
			Collections.sort(nomList, sort);
		}
		if(caseInfo.getInfo_assistant_police_two()==null||caseInfo.getInfo_assistant_police_two().isEmpty()) {//如果第一协办为设定则设定
			if((countNom/countCap>=3&&capList.size()>0)||nomList.size()<=0) {
				ajdbxt_police police=capList.get(0).getKey();
				caseInfo.setInfo_assistant_police_two(police.getAjdbxt_police_id());				
			}else if(capList.size()<=0||(countNom/countCap<3&&nomList.size()>0)) {
				ajdbxt_police police=nomList.get(0).getKey();
				caseInfo.setInfo_assistant_police_two(police.getAjdbxt_police_id());				
			}else {
				caseInfo.setInfo_main_police(chief.getAjdbxt_police_id());
			}
		}
		ProcessDTO processDTO=new ProcessDTO();
		processDTO.setInfo(caseInfo);
		List<ajdbxt_police> policelist=new ArrayList<>();
		policelist.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_main_police()));
		policelist.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_assistant_police_one()));
		policelist.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_assistant_police_two()));
		processDTO.setPolice(polices);
		processDTO.setDepartment(infoDepartmentDao.findDepartmentById(caseInfo.getInfo_department()));
		return JsonUtils.toJson(processDTO);
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
			if(three!=null&&(three.isEmpty()==false)) {
				policeList.add(infoPoliceDao.findPoliceById(three));
			}
			policeList.add(infoPoliceDao.findPoliceById(info.getInfo_legal_team_member()));
			policeList.add(infoPoliceDao.findPoliceById(info.getInfo_bureau_leader()));
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
	public String save(ajdbxt_info caseInfo) {
		ProcessDTO processDTO=new ProcessDTO();
		caseInfo.setAjdbxt_info_id(UUID.randomUUID().toString());
		caseInfo.setInfo_gmt_ceate(util.Time.getStringSecond());
		caseInfo.setInfo_gmt_modify(caseInfo.getInfo_gmt_ceate());//保存时将修改时间设为创建时间
		processDTO.setInfo(caseInfo);
		List<ajdbxt_police> policeList=new LinkedList<>();
		policeList.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_main_police()));
		if(caseInfo.getInfo_assistant_police_one()==null&&(caseInfo.getInfo_assistant_police_one().isEmpty()==false)) {
			policeList.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_assistant_police_one()));
			if(caseInfo.getInfo_assistant_police_two()==null&&(caseInfo.getInfo_assistant_police_two().isEmpty()==false)) {
				policeList.add(infoPoliceDao.findPoliceById(caseInfo.getInfo_assistant_police_two()));
			}
		}
		int l=policeList.size();
		for(int index=0;index<l;index++) {
			ajdbxt_police police=policeList.get(index);
			if(police.getPolice_duty().contains("所长")||police.getPolice_duty().contains("队长")) {
				ajdbxt_police pT=policeList.get(0);
				policeList.set(0, police);
				policeList.set(index, pT);
				break;
			}else if((police.getPolice_duty().contains("副所长")||police.getPolice_duty().contains("副队长"))) {
				ajdbxt_police pT=policeList.get(0);
				if((pT.getPolice_duty().contains("所长")||pT.getPolice_duty().contains("队长")==false)
						&&((pT.getPolice_duty().contains("所长")||pT.getPolice_duty().contains("队长"))==false)) {
					policeList.set(0, police);
					policeList.set(index, pT);
					break;
				}
			}
		}
		for(int index=0;index<l;index++) {
			ajdbxt_police police=policeList.get(index);
			if(index==0) {
				caseInfo.setInfo_main_police(police.getAjdbxt_police_id());
			}else if(index==1) {
				caseInfo.setInfo_assistant_police_one(police.getAjdbxt_police_id());
			}else {
				caseInfo.setInfo_assistant_police_two(police.getAjdbxt_police_id());
			}
		}
		
		processDao.saveProcessByCaseId(caseInfo.getAjdbxt_info_id());
//		processDTO.setProcess(processDao.findProcessByCaseId(caseInfo.getAjdbxt_info_id()).get(0));
		processDTO.setDepartment(infoDepartmentDao.findDepartmentById(caseInfo.getInfo_department()));
		infoDao.saveCase(caseInfo);
		if(processDao.findProcessByCaseId(caseInfo.getAjdbxt_info_id()).size()<=0) {
			processDao.saveProcessByCaseId(caseInfo.getAjdbxt_info_id());
		}
		return JsonUtils.toJson(processDTO);
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
	@Override
	public String update(ajdbxt_info info) {
		infoDao.saveCase(info);
		processDao.saveProcessByCaseId(info.getAjdbxt_info_id());
		ProcessDTO processDTO=new ProcessDTO();
		processDTO.setInfo(info);
		processDTO.setDepartment(infoDepartmentDao.findDepartmentById(info.getInfo_department()));
		List<ajdbxt_process> processList=processDao.findProcessByCaseId(info.getAjdbxt_info_id());
		if(processList.size()>0) {
			processDTO.setProcess(processList.get(0));
		}
		List<ajdbxt_police> policeList=new LinkedList<>();
		policeList.add(infoPoliceDao.findPoliceById(info.getInfo_main_police()));
		if(info.getInfo_assistant_police_one()==null&&info.getInfo_assistant_police_one().isEmpty()==false) {
			policeList.add(infoPoliceDao.findPoliceById(info.getInfo_assistant_police_one()));
			if(info.getInfo_assistant_police_two()==null&&info.getInfo_assistant_police_two().isEmpty()==false) {
				policeList.add(infoPoliceDao.findPoliceById(info.getInfo_assistant_police_two()));
			}
		}
		processDTO.setProcess(processDao.findProcessByCaseId(info.getAjdbxt_info_id()).get(0));
		processDTO.setPolice(policeList);
		ApplicationContext applicationContext=(ApplicationContext) ServletActionContext.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		boolean caseFiled=false;
		if(info.getInfo_category().equals("行政案件")) {
			caseFiled=true;
		}
		new SMSThread(MsgSend.SUBPOENA_A_SUSPECT_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();//通知
		return JsonUtils.toJson(processDTO);
	}
	
}

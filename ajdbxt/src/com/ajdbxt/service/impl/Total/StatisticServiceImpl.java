package com.ajdbxt.service.impl.Total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ajdbxt.dao.Total.StatisticDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticDepartmentCaseNumDTO;
import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseDto;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;
import com.ajdbxt.domain.VO.Total.PoliceCaseStatisticVo;
import com.ajdbxt.service.Total.StatisticService;

public class StatisticServiceImpl implements StatisticService {
	private StatisticDao statisticDao;
	
	/*
	 * 办案部门的案件统计
	 */
	@Override
	public DepartmentStatisticVo statisticDepartmentCase(DepartmentStatisticVo departmentStatisticVo) {
		List<StatisticDepartmentCaseNumDTO> listDepartmentCaseDto=new ArrayList<StatisticDepartmentCaseNumDTO>();
		List<ajdbxt_department> departmentList=new ArrayList<ajdbxt_department>();
		//得到所有的部门
		departmentList=statisticDao.findAllDepartment();
		//将值封装到Dto
		for(int i=0;i<departmentList.size();i++) {
			StatisticDepartmentCaseNumDTO statisticDepartmentCaseNumDTO =new StatisticDepartmentCaseNumDTO ();
			statisticDepartmentCaseNumDTO.setDepartment(departmentList.get(i));
			int adminCase=statisticDao.findcase(departmentStatisticVo, "行政案件", departmentList.get(i).getAjdbxt_department_id());
			int criminCase=statisticDao.findcase(departmentStatisticVo, "刑事案件", departmentList.get(i).getAjdbxt_department_id());
			int totalCase=adminCase+criminCase;
			double totalScore=statisticDao.findTotalScoreByDepartment(departmentStatisticVo,departmentList.get(i).getAjdbxt_department_id());
			statisticDepartmentCaseNumDTO.setDepartment(departmentList.get(i));
			statisticDepartmentCaseNumDTO.setAdminCase(adminCase);
			statisticDepartmentCaseNumDTO.setCriminalCase(criminCase);
			statisticDepartmentCaseNumDTO.setTotalCase(totalCase);
			if(totalCase==0) {
				statisticDepartmentCaseNumDTO.setAverageScore("0");
			}else {
			statisticDepartmentCaseNumDTO.setAverageScore(String.format("%.2f", (totalScore/totalCase)));//保留两位小数
			}
			listDepartmentCaseDto.add(statisticDepartmentCaseNumDTO);
		}
		//排序
		//"0"代表平均分  "1" 代表行政案件  "2"代表刑事案件
		if(departmentStatisticVo.getOrderString().trim()==null || departmentStatisticVo.getOrderString().trim().equals("0")) {
			Collections.sort(listDepartmentCaseDto, new Comparator<StatisticDepartmentCaseNumDTO>() {

				@Override
				public int compare(StatisticDepartmentCaseNumDTO o1, StatisticDepartmentCaseNumDTO o2) {
					if(Double.parseDouble(o1.getAverageScore())>Double.parseDouble(o2.getAverageScore())) {
						return -1;
					}else if(Double.parseDouble(o1.getAverageScore())<Double.parseDouble(o2.getAverageScore())) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}
		else if(departmentStatisticVo.getOrderString().trim().equals("1")) {
			Collections.sort(listDepartmentCaseDto,new Comparator<StatisticDepartmentCaseNumDTO>() {

				@Override
				public int compare(StatisticDepartmentCaseNumDTO dto1, StatisticDepartmentCaseNumDTO dto2) {
						if(dto1.getAdminCase()>dto2.getAdminCase()) {
							return -1;
						}else if(dto1.getAdminCase()<dto2.getAdminCase()){
							return 1;
						}else {
							return 0;
						}
				}
			
			});
		}else if(departmentStatisticVo.getOrderString().trim().equals("2")){
			Collections.sort(listDepartmentCaseDto, new Comparator<StatisticDepartmentCaseNumDTO>() {

				@Override
				public int compare(StatisticDepartmentCaseNumDTO o1, StatisticDepartmentCaseNumDTO o2) {
					if(o1.getCriminalCase()>o2.getCriminalCase()) {
						return -1;
					}else if(o1.getCriminalCase()<o2.getCriminalCase()) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}
		
		departmentStatisticVo.setStatisticDepartmentCaseNumDTO(listDepartmentCaseDto);
		//分页
		/*List<StatisticDepartmentCaseNumDTO> newListDepartmentCaseDto=new ArrayList<StatisticDepartmentCaseNumDTO>();
		for(int i=(departmentStatisticVo.getCurrePage()-1)*departmentStatisticVo.getPageSize();
				i<departmentStatisticVo.getCurrePage()*departmentStatisticVo.getPageSize();i++) {
			if(i<listDepartmentCaseDto.size()) {
				newListDepartmentCaseDto.add(listDepartmentCaseDto.get(i));
			}else {
				break;
			}
		}
		
		departmentStatisticVo.setStatisticPoliceCaseNumDTO(newListDepartmentCaseDto);
		
		//总记录数;
		 int i= listDepartmentCaseDto.size();
		 departmentStatisticVo.setTotalRecords(i);
		 departmentStatisticVo.setTotalPages(((i-1)/departmentStatisticVo.getPageSize())+1);
		 if(departmentStatisticVo.getCurrePage()<=1) {
			 departmentStatisticVo.setHasPrePage(false);
		 }else {
			 departmentStatisticVo.setHasPrePage(true);
		 }
		 if(departmentStatisticVo.getCurrePage()>=departmentStatisticVo.getTotalPages()) {
			 departmentStatisticVo.setHasNextPage(false);
		 }else {
			 departmentStatisticVo.setHasNextPage(true);
		 }*/
		return departmentStatisticVo;
	}

	/*警员统计*/
	@Override
	public PoliceCaseStatisticVo statisticPoliceCase(PoliceCaseStatisticVo policeCaseStatisticVo) {
		List<StatisticPoliceCaseDto> statisticCaseByPoliceList=new ArrayList<StatisticPoliceCaseDto>();
		List<ajdbxt_police> listPolice=new ArrayList<ajdbxt_police>();
		listPolice=statisticDao.findAllPolice(policeCaseStatisticVo);
		for(int i=0;i<listPolice.size();i++) {
			StatisticPoliceCaseDto statisticCaseByPoliceDTO=new StatisticPoliceCaseDto();
			statisticCaseByPoliceDTO.setPolice(listPolice.get(i));
			statisticCaseByPoliceDTO.setDepartment(statisticDao.findPoliceDepartment(listPolice.get(i).getPolice_department()));
			//行政主办案件数
			int adminMianCaseNum=statisticDao.findPoliceMainCaseNum(policeCaseStatisticVo, listPolice.get(i).getAjdbxt_police_id(), "行政案件");
			//刑事主办案件数
			int crimalMianCaseNum=statisticDao.findPoliceMainCaseNum(policeCaseStatisticVo, listPolice.get(i).getAjdbxt_police_id(), "刑事案件");
			statisticCaseByPoliceDTO.setAdminMianCase(adminMianCaseNum);
			statisticCaseByPoliceDTO.setAdminAsistCase(statisticDao.findPoliceAsistCaseNum(policeCaseStatisticVo, listPolice.get(i).getAjdbxt_police_id(), "行政案件"));
			statisticCaseByPoliceDTO.setCrimalMainCase(crimalMianCaseNum);
			statisticCaseByPoliceDTO.setCrimalAsistCase(statisticDao.findPoliceAsistCaseNum(policeCaseStatisticVo, listPolice.get(i).getAjdbxt_police_id(), "刑事案件"));
			double averageScore=statisticDao.findTotalScoreByPolice(policeCaseStatisticVo, listPolice.get(i).getAjdbxt_police_id());
			statisticCaseByPoliceDTO.setScore_mian(Double.parseDouble(String.format("%.2f",averageScore)));
			statisticCaseByPoliceList.add(statisticCaseByPoliceDTO);
		}
		policeCaseStatisticVo.setStatisticPoliceCaseDto(statisticCaseByPoliceList);
		
		//排序
		//"0"代表平均分 "1"主办行政案件 "2"主办刑事案件 "3"协办行政案件 "4"协办刑事案件
		if(policeCaseStatisticVo.getOrderString().trim()==null || policeCaseStatisticVo.getOrderString().trim().equals("0")) {
			Collections.sort(statisticCaseByPoliceList, new Comparator<StatisticPoliceCaseDto>() {

				@Override
				public int compare(StatisticPoliceCaseDto o1, StatisticPoliceCaseDto o2) {
					if(o1.getScore_mian()>o2.getScore_mian()) {
						return -1;
					}else if(o1.getScore_mian()<o2.getScore_mian()) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}else if(policeCaseStatisticVo.getOrderString().trim().equals("1")) {
			Collections.sort(statisticCaseByPoliceList,new Comparator<StatisticPoliceCaseDto>() {

				@Override
				public int compare(StatisticPoliceCaseDto o1, StatisticPoliceCaseDto o2) {
					if(o1.getAdminMianCase()>o2.getAdminMianCase()) {
						return -1;
					}else if(o1.getAdminMianCase()<o2.getAdminMianCase()) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}else if(policeCaseStatisticVo.getOrderString().trim().equals("2")) {
			Collections.sort(statisticCaseByPoliceList,new Comparator<StatisticPoliceCaseDto>() {

				@Override
				public int compare(StatisticPoliceCaseDto o1, StatisticPoliceCaseDto o2) {
					if(o1.getCrimalMainCase()>o2.getCrimalMainCase()) {
						return -1;
					}else if(o1.getCrimalMainCase()<o2.getCrimalMainCase()) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}else if(policeCaseStatisticVo.getOrderString().trim().equals("3")) {
			Collections.sort(statisticCaseByPoliceList,new Comparator<StatisticPoliceCaseDto>() {
				@Override
				public int compare(StatisticPoliceCaseDto o1, StatisticPoliceCaseDto o2) {
					if(o1.getAdminAsistCase()>o2.getAdminAsistCase()) {
						return -1;
					}else if(o1.getAdminAsistCase()<o2.getAdminAsistCase()) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}else if(policeCaseStatisticVo.getOrderString().trim().equals("4")) {
			Collections.sort(statisticCaseByPoliceList,new Comparator<StatisticPoliceCaseDto>() {

				@Override
				public int compare(StatisticPoliceCaseDto o1, StatisticPoliceCaseDto o2) {
					if(o1.getCrimalAsistCase()>o2.getCrimalAsistCase()) {
						return -1;
					}else if(o1.getCrimalAsistCase()<o2.getCrimalAsistCase()) {
						return 1;
					}else {
						return 0;
					}
				}
				
			});
		}
		//分页
		List<StatisticPoliceCaseDto> newStatisticPoliceCaseDto=new ArrayList<StatisticPoliceCaseDto>();
		for(int i=(policeCaseStatisticVo.getCurrePage()-1)*policeCaseStatisticVo.getPageSize();
				i<policeCaseStatisticVo.getCurrePage()*policeCaseStatisticVo.getPageSize();i++) {
			if(i<statisticCaseByPoliceList.size()) {
				newStatisticPoliceCaseDto.add(statisticCaseByPoliceList.get(i));
			}else {
				break;
			}
		}
		policeCaseStatisticVo.setStatisticPoliceCaseDto(newStatisticPoliceCaseDto);
		
		int i= statisticCaseByPoliceList.size();
		policeCaseStatisticVo.setTotalRecords(i);
		policeCaseStatisticVo.setTotalPages(((i-1)/policeCaseStatisticVo.getPageSize())+1);
		 if(policeCaseStatisticVo.getCurrePage()<=1) {
			 policeCaseStatisticVo.setHasPrePage(false);
		 }else {
			 policeCaseStatisticVo.setHasPrePage(true);
		 }
		 if(policeCaseStatisticVo.getCurrePage()>=policeCaseStatisticVo.getTotalPages()) {
			 policeCaseStatisticVo.setHasNextPage(false);
		 }else {
			 policeCaseStatisticVo.setHasNextPage(true);
		 }
		 
		return policeCaseStatisticVo;
	}
	
	public StatisticDao getStatisticDao() {
		return statisticDao;
	}
	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

}

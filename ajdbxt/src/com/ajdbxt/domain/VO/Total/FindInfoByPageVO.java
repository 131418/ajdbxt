package com.ajdbxt.domain.VO.Total;

import java.util.List;
public class FindInfoByPageVO {
	
	private List list;//要返回的某一页的记录列表
	
	private int totalRecords;//总记录数
	
	private int totalPage;//总页数
	
	private int currentPage;//当前页
	
	private int pageSize;//每页记录数
	
	private String start_time = "0000-00-00";//开始时间

	private String stop_time = "9999-99-99";//结束时间
	
	private boolean isFirstPage;//是否为第一页
	
	private boolean isLastPage;//是否为最后一页
	
	private boolean hasPreviousPage;//是否有前一页
	
	private boolean hasNextPage;//是否有下一页

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	/*
	 * 初始化分页信息
	 * 
	 */
	
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}
	
	/*
	 * 以下判断页面信息，只需要getter方法即可
	 */

	private boolean isFirstPage() {
		// TODO Auto-generated method stub
		return currentPage == 1; // 如是当前页是第1页
	}

	private boolean isLastPage() {
		// TODO Auto-generated method stub
		return currentPage == totalPage; // 如果当前页是最后一页
	}

	private boolean isHasPreviousPage() {
		// TODO Auto-generated method stub
		return currentPage != 1;// 只要当前页不是第1页
	}

	private boolean isHasNextPage() {
		// TODO Auto-generated method stub
		return currentPage != totalPage; // 只要当前页不是最后1页
	}
	
	
	/*
	 * 计算总页数，静态方法，供外部直接通过类名调用
	
	@param pageSize每页记录数
	@param allRow总记录数
	@return 总页数
	*/

	public static int countTotalPage(final int pageSize, final int allRow) {
		//int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
		int totalPage = (allRow-1)/pageSize + 1;

		return totalPage;
	}
	
	
	 /*计算当前页开始记录
	 @param pageSize每页记录数
	 @param currentPage当前第几页
	 @return 当前页开始记录号*/
	
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}
	
	/*计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	@paramPage 传入的参数(可能为空,即0,则返回1)
	@return 当前页*/
			
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}


	
	

	
	
}


package com.ajdbxt.domain.VO.Process;

import java.util.List;

import com.ajdbxt.domain.DTO.Process.ProcessDTO;


public class ProcessVO {
		// 当前页
		private int pageIndex = 1;

		// 总记录数
		private int totalRecords = 0;

		// 每页显示记录数
		private int pageSize = 10;
		
		// 总页数
		private int totalPages = 1;

		// 是否有上一页
		private boolean HavePrePage = false;

		// 是否有下一页
		private boolean HaveNextPage = false;
		
		private List<ProcessDTO> processDTOList;
		
		
		public List<ProcessDTO> getProcessDTOList() {
			return processDTOList;
		}

		public void setProcessDTOList(List<ProcessDTO> processDTOList) {
			this.processDTOList = processDTOList;
		}

		public int getPageIndex() {
			return pageIndex;
		}

		public void setPageIndex(int pageIndex) {
			this.pageIndex = pageIndex;
		}

		public int getTotalRecords() {
			return totalRecords;
		}

		public void setTotalRecords(int totalRecords) {
			this.totalRecords = totalRecords;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}

		public boolean isHavePrePage() {
			return HavePrePage;
		}

		public void setHavePrePage(boolean havePrePage) {
			HavePrePage = havePrePage;
		}

		public boolean isHaveNextPage() {
			return HaveNextPage;
		}

		public void setHaveNextPage(boolean haveNextPage) {
			HaveNextPage = haveNextPage;
		}

		

		@Override
		public String toString() {
			return "MajorManagementVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize=" + pageSize
					+ ", totalPages=" + totalPages + ", HavePrePage=" + HavePrePage + ", HaveNextPage=" + HaveNextPage + "]";
		}
}

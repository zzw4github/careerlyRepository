package com.careerly.common.page;

/**   
* @Title: PageBean.java 
* @Package com.careerly.common.page 
* @Description: java分页功能
* @author careerly
*/ 
public class PageBean {

	private int currentPage = 1;// 当前页数

	public int totalPages = 0;// 总页数

	private int pageSize = 10;// 每页显示数

	private int totalRows = 0;// 总数据数

	private int startNum = 0;// 开始记录

	private int nextPage = 0;// 下一页

	private int previousPage = 0;// 上一页

	private boolean hasNextPage = false;// 是否有下一页

	private boolean hasPreviousPage = false;// 是否有前一页
	
	
	public PageBean(int pageSize, int currentPage, int totalRows) {

		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRows = totalRows;
	} 
	
	public PageBean(int currentPage, int totalRows) {
		
		this.currentPage = currentPage;
		this.totalRows = totalRows;
	} 
	
	public PageBean() {
		
	} 
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @author careerly
	 * @Description:获取总页数
	 * @returnType int
	 * @throws
	 */ 
	public int getTotalPages() {
		if ((totalRows % pageSize) == 0) {
			totalPages = totalRows / pageSize;
		} else {
			totalPages = totalRows / pageSize + 1;
		}
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @author careerly
	 * @Description:获取开始记录
	 * @returnType int
	 * @throws
	 */ 
	public int getStartNum() {
		startNum = (currentPage - 1) * pageSize;
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	/**
	 * @author careerly
	 * @Description: 获取下一页
	 * @returnType int
	 * @throws
	 */ 
	public int getNextPage() {
		nextPage = currentPage + 1;
		if (nextPage >= totalPages) {
		nextPage = totalPages;
		}
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @author careerly
	 * @Description: 获取上一页
	 * @returnType int
	 * @throws
	 */ 
	public int getPreviousPage() {
		previousPage = currentPage - 1;
		if (previousPage <= 1) {
			previousPage = 1;
		}
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	/**
	 * @author careerly
	 * @Description: 判断是否有下一页
	 * @returnType boolean
	 * @throws
	 */ 
	public boolean isHasNextPage() {
		if (currentPage >= totalPages) {
			hasNextPage = false;
			currentPage = totalPages;
		} else {
			hasNextPage = true;
		}
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @author careerly
	 * @Description: 判断是否有上一页
	 * @returnType boolean
	 * @throws
	 */ 
	public boolean isHasPreviousPage() {
		if (currentPage <= 1) {
			hasPreviousPage = false;
			currentPage = 1;
		} else {
			hasPreviousPage = true;
		}
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	
	

}

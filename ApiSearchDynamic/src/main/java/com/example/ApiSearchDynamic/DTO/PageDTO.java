package com.example.ApiSearchDynamic.DTO;

import java.util.List;



public class PageDTO<T> {
	
	private int pageNum;
    private int pageSize;
    private int totalPage;
    private List<T> ListPage;
    private List<T> content;
    
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getListPage() {
		return ListPage;
	}

	public void setListPage(List<T> listPage) {
		ListPage = listPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public PageDTO() {
		super();
	}

	public PageDTO(int pageNum, int pageSize, int totalPage, List<T> listPage) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		ListPage = listPage;
		
	}
	public PageDTO(List<T> content, int pageNum2, int pageSize2, int totalPages) {
		super();
		ListPage = content;
	}

	@Override
	public String toString() {
		return "PageDTO [pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", ListPage="
				+ ListPage + "]";
	}
	
    
	 
	 
    
    
}

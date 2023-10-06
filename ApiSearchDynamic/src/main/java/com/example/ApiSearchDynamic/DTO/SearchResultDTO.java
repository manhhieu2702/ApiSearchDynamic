package com.example.ApiSearchDynamic.DTO;

import java.util.List;

public class SearchResultDTO<T> {
	
	
    private List<T> data;
    private int pageNum;
    private int pageSize;
    private int totalPage;
    
    
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
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
	@Override
	public String toString() {
		return "SearchResultDTO [data=" + data + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalPage="
				+ totalPage + "]";
	}
	public SearchResultDTO() {
		super();
	}
	public SearchResultDTO(List<T> data, int pageNum, int pageSize, int totalPage) {
		super();
		this.data = data;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
	}
    
    
}

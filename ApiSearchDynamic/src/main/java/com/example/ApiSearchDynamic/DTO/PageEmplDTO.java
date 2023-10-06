package com.example.ApiSearchDynamic.DTO;


import java.util.List;

import com.example.ApiSearchDynamic.Model.Employee;

public class PageEmplDTO extends Employee {
    public List<Employee> employees;
    private int pageNum;
    private int pageSize;
    private int totalPage;
    
    
    
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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
	public PageEmplDTO() {
		
	}
	public PageEmplDTO(List<Employee> employees, int pageNum, int pageSize, int totalPage) {
		
		this.employees = employees;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
	}

    
    
}

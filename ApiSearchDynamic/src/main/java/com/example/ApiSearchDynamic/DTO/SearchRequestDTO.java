package com.example.ApiSearchDynamic.DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;



public class SearchRequestDTO {
	
	  private String name;
	  
	  private String address;
	  
	  private String phone;
	  
	  private String email;
	  
	  private Integer age;
	  
	  private String department;
	  
	  @JsonProperty("start_date_start")
	  private Date startDate;
	  
	  @JsonProperty("start_date_end")
	  private Date endDate;
	  
	  private int pageNum;
	  
	  private int pageSize;
	  
	  private int pageTotal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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



	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}




	@Override
	public String toString() {
		return "SearchRequestDTO [name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", age=" + age + ", department=" + department + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", pageTotal=" + pageTotal + "]";
	}

	public SearchRequestDTO() {
		super();
	}

	public SearchRequestDTO(String name, String address, String phone, String email,Integer age, String department,
			Date startDate, Date endDate, int pageNum, int pageSize,int pageTotal) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.department = department;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pageTotal =pageTotal;
	}
	  
	public String toCriteriaString() {
		
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		
        Map<String, Object> criteriaMap = new HashMap<>();
        criteriaMap.put("name_epl", name);
        criteriaMap.put("email_epl", email);
        criteriaMap.put("address_epl", address);
        criteriaMap.put("age_epl", age);
        criteriaMap.put("phone_epl", phone);
        criteriaMap.put("department_epl", department);
        criteriaMap.put("start_date_start",sp.format(startDate));
        criteriaMap.put("start_date_end",sp.format(endDate));
        criteriaMap.put("pageNum", pageNum);
        criteriaMap.put("pageSize", pageSize);
        criteriaMap.put("pageTotal", pageTotal);
        
        return new Gson().toJson(criteriaMap);
    } 
}

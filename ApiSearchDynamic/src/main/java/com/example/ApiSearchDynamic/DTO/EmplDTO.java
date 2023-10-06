package com.example.ApiSearchDynamic.DTO;



import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class EmplDTO {
    private String name;

    private String email;

    private String address;

    private Integer age;

    private String phone;

    private String department;

    private Integer pageNum;

    private Integer pageSize;
    
    private Integer pageTotal;
    
	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "EmplDTO [name=" + name + ", email=" + email + ", address=" + address + ", age=" + age + ", phone="
				+ phone + ", department=" + department + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", pageTotal=" + pageTotal + "]";
	}

	public EmplDTO() {
		super();
	}

	public EmplDTO(String name, String email, String address, Integer age, String phone, String department,
			Integer pageNum, Integer pageSize, Integer pageTotal) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.age = age;
		this.phone = phone;
		this.department = department;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pageTotal = pageTotal;
	}

    public String toCriteriaString() {
        Map<String, Object> criteriaMap = new HashMap<>();
        criteriaMap.put("name_epl", name);
        criteriaMap.put("email_epl", email);
        criteriaMap.put("address_epl", address);
        criteriaMap.put("age_epl", age);
        criteriaMap.put("phone_epl", phone);
        criteriaMap.put("department_epl", department);
        criteriaMap.put("pageNum", pageNum);
        criteriaMap.put("pageSize", pageSize);
        criteriaMap.put("pageTotal", pageTotal);
        
        return new Gson().toJson(criteriaMap);
    }
    
}

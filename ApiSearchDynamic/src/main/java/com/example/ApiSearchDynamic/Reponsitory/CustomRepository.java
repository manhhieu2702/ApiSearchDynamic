package com.example.ApiSearchDynamic.Reponsitory;

import java.util.List;

import com.example.ApiSearchDynamic.Model.Employee;

public interface CustomRepository {
	
	 public List<Employee> searchEmployeesProc(String searchRequestDTO);
}

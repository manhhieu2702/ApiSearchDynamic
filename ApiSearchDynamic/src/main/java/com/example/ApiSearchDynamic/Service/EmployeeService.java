package com.example.ApiSearchDynamic.Service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.example.ApiSearchDynamic.DTO.SearchRequestDTO;
import com.example.ApiSearchDynamic.DTO.SearchResultDTO;

import com.example.ApiSearchDynamic.Model.Employee;


public interface EmployeeService {
	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	List<Employee> getEmployeesByName(String name);

	Employee createEmployee(Employee employee);

	Employee updateEmployee(Long id, Employee employee);

	void deleteEmployee(Long id);

//<1>
	SearchResultDTO <Employee> searchEmployeesSpecification(SearchRequestDTO searchRequestDTO);

//<2.1>
	Page<Employee> searchEmployeesNative(Employee employee, Pageable pageable);

//<2.2> StringBuilder 
	List<Employee> getEmployeesByPage(SearchRequestDTO emplPageDTO);

//<3> New fix Store Procedure

	SearchResultDTO<?> searchEmployeesPROC(SearchRequestDTO searchRequestDTO);

}

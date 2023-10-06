package com.example.ApiSearchDynamic.Reponsitory;



import java.sql.Date;
import java.time.LocalDate;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


import com.example.ApiSearchDynamic.Model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{

	List<Employee> findByName(String name);
	
	
	
//<2 native query>	/*-----------------------------------*/
	/* <2> viết api tìm kiếm động sử dụng native query: viết sql trên java : cái này a Tuấn bảo viết cho vui cho biết */
	 @Query(value = "SELECT * FROM tbl_employeee WHERE " +
	            "(name_epl = :name) OR " +
	            "(email_epl = :email) OR " +
	            "(address_epl = :address) OR " +
	            "(age_epl = :age) OR "  +
	            "(phone_epl = :phone) OR " +
	            "(department_epl = :department) OR " +
	            "(start_date_epl = :startDate)",
	            nativeQuery = true)
	    List<Employee> searchEmployeesNative(
	            @Param("name") String name,
	            @Param("email") String email,
	            @Param("address") String address,
	            @Param("age") Integer age,
	            @Param("phone") String phone,
	            @Param("department") String department,
	            @Param("startDate") Date localDate
	    );

	 

	 

// <3 call store procedure JPA>	/*------------------------------------------*/

	    // Định nghĩa phương thức gọi stored procedure
	    @Procedure(procedureName  = "myschema.get_employee_data_dynamic")
	    List<Employee> getEmployeeDataDynamic(String ref,String criteriaString);
}

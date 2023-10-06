package com.example.ApiSearchDynamic.Controller;



import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiSearchDynamic.DTO.PageDTO;
import com.example.ApiSearchDynamic.DTO.SearchRequestDTO;
import com.example.ApiSearchDynamic.DTO.SearchResultDTO;
import com.example.ApiSearchDynamic.Model.Employee;
import com.example.ApiSearchDynamic.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
    
    @GetMapping("/search")
    public List<Employee> getEmployeesByName(@RequestParam String name) {
        return employeeService.getEmployeesByName(name);
    }
    
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
    
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
    
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    
	/* <1> Thực hiện viết api tìm kiếm động theo nhiều tiêu chí dùng jpa*/
        
    @PostMapping("/searchSpecification")
    public SearchResultDTO<Employee> searchEmployees(@RequestBody SearchRequestDTO searchRequestDTO) {
        return employeeService.searchEmployeesSpecification(searchRequestDTO);
    }
    
    
    
	/* <2> viết api tìm kiếm động sử dụng native query: viết sql trên java : cái này a Tuấn bảo viết cho vui cho biết */
    
    
    
    
    @GetMapping("search2/{pageNum}/{pageSize}")
    public ResponseEntity<PageDTO<Employee>> searchEmployeesNative(@RequestBody Employee employee,@PathVariable int pageNum,@PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Page<Employee> employees= employeeService.searchEmployeesNative(employee, pageable);
        PageDTO<Employee> pageDTO = new PageDTO<>();
        pageDTO.setPageNum(pageNum);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPage(employees.getTotalPages());
        pageDTO.setContent(employees.getContent());
        return ResponseEntity.ok(pageDTO);
    }
    
    
    
    /* <2> viết api tìm kiếm động sử dụng native query: viết sql trên java + string builder +entity management */
    @SuppressWarnings("rawtypes")
	@PostMapping("/searchNative")
    public SearchResultDTO getEmployeesByPage(@RequestBody SearchRequestDTO searchRequestDTO) {
        List<Employee> employees = employeeService.getEmployeesByPage(searchRequestDTO);
        
       
        int totalEmployees = employees.size(); 
        
        int totalPages = (int) Math.ceil((double) totalEmployees / searchRequestDTO.getPageSize());
        
        // Thiết lập thông tin phân trang vào đối tượng EmplPageDTO
        @SuppressWarnings("unchecked")
		SearchResultDTO<Employee> searchResult= new SearchResultDTO();
        
        searchResult.setData(employees);
        searchResult.setPageNum(searchRequestDTO.getPageNum());
        searchResult.setPageSize(searchRequestDTO.getPageSize());
        searchResult.setTotalPage(totalPages);
        
        return searchResult;
    }
    
    
	/* <3> viết api tìm kiếm động sử dụng java call procedure */

    @SuppressWarnings({ "rawtypes" })
    
    
	@PostMapping("/searchProcPA")
    public SearchResultDTO searchEmployeesPROC(@RequestBody SearchRequestDTO searchRequestDTO) {
        return employeeService.searchEmployeesPROC(searchRequestDTO);
    }
    
    
    

    
}

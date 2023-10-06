package com.example.ApiSearchDynamic.Service.Implement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.ApiSearchDynamic.DTO.SearchRequestDTO;
import com.example.ApiSearchDynamic.DTO.SearchResultDTO;
import com.example.ApiSearchDynamic.Model.Employee;
import com.example.ApiSearchDynamic.Reponsitory.CustomRepository;
import com.example.ApiSearchDynamic.Reponsitory.EmployeeRepository;
import com.example.ApiSearchDynamic.Service.EmployeeService;


import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import jakarta.persistence.criteria.Predicate;

@Service
@Transactional
public class EmployeeServiceImple implements EmployeeService {
	@Autowired
	private  EmployeeRepository employeeRepos;
      
    public EmployeeRepository getEmployeeRepository() {
		return employeeRepos;
	}

	@Override
    public List<Employee> getAllEmployees() {
        return employeeRepos.findAll();
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepos.findById(id).orElse(null);
    }
    
    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepos.findByName(name);
    }
    
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepos.save(employee);
    }
    
    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepos.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setStartDate(employee.getStartDate());
            
            return employeeRepos.save(existingEmployee);
        }
        return null;
    }
    
    @Override
    public void deleteEmployee(Long id) {
    	employeeRepos.deleteById(id);
    }
    
    
//  <1>  truyen vao body vs like va equal


    @Override
    public SearchResultDTO<Employee> searchEmployeesSpecification(SearchRequestDTO searchRequestDTO) {
        Specification<Employee> spec = Specification.where((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchRequestDTO.getName() != null && !searchRequestDTO.getName().isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + searchRequestDTO.getName() + "%"));
            }
            if (searchRequestDTO.getAddress() != null && !searchRequestDTO.getAddress().isEmpty()) {
                predicates.add(cb.like(root.get("address"), "%" + searchRequestDTO.getAddress() + "%"));
            }
            if (searchRequestDTO.getPhone() != null && !searchRequestDTO.getPhone().isEmpty()) {
                predicates.add(cb.equal(root.get("phone"), searchRequestDTO.getPhone()));
            }
            if (searchRequestDTO.getEmail() != null && !searchRequestDTO.getEmail().isEmpty()) {
                predicates.add(cb.equal(root.get("email"), searchRequestDTO.getEmail()));
            }
            if (searchRequestDTO.getAge() > 0 ) {
                predicates.add(cb.equal(root.get("age"),  searchRequestDTO.getAge()));
            }
            if (searchRequestDTO.getDepartment() != null && !searchRequestDTO.getDepartment().isEmpty()) {
                predicates.add(cb.like(root.get("department"), "%" + searchRequestDTO.getDepartment() + "%"));
            }       
            if (searchRequestDTO.getStartDate() != null && searchRequestDTO.getEndDate() != null) {
                predicates.add(
                        cb.between(root.get("startDate"), searchRequestDTO.getStartDate(), searchRequestDTO.getEndDate()));
            }
            if (searchRequestDTO.getStartDate() != null && searchRequestDTO.getEndDate() == null) {
            	
            	predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), searchRequestDTO.getStartDate()));
            }
            if (searchRequestDTO.getEndDate() != null && searchRequestDTO.getStartDate() == null) {
            	
            	predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), searchRequestDTO.getEndDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });

        Pageable pageable = PageRequest.of(searchRequestDTO.getPageNum() - 1, searchRequestDTO.getPageSize());
        Page<Employee> page = employeeRepos.findAll(spec, pageable);

        SearchResultDTO<Employee> searchResult = new SearchResultDTO<>();
        searchResult.setData(page.getContent());
        searchResult.setPageNum(page.getNumber() + 1);
        searchResult.setPageSize(page.getSize());
        searchResult.setTotalPage(page.getTotalPages());

        return searchResult;
    }
      
//  <2>   Kieu nay lam cho biet native query 
    /* <2> viết api tìm kiếm động sử dụng native query: viết sql trên java : cái này a Tuấn bảo viết cho vui cho biết */
    public Page<Employee> searchEmployeesNative(Employee employee, Pageable pageable) {
        List<Employee> employees = employeeRepos.searchEmployeesNative(
                employee.getName(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getAge(),
                employee.getPhone(),
                employee.getDepartment(),
                employee.getStartDate()
        );

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Employee> pageEmployees;

        if (employees.size() < startItem) {
            pageEmployees = List.of();
        } else {
            int toIndex = Math.min(startItem + pageSize, employees.size());
            pageEmployees = employees.subList(startItem, toIndex);
        }

        return new PageImpl<>(pageEmployees, pageable, employees.size());
    }
    
//<2> Native query su dung entity manager + stringbuider (viet kieu nay thi khong phai goi @query ben Repository nua)
    @PersistenceContext
    private EntityManager entityManager;
     
    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
    public List<Employee> getEmployeesByPage(SearchRequestDTO searchRequestDTO) {
        int offset = (searchRequestDTO.getPageNum() - 1) * searchRequestDTO.getPageSize();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM tbl_employeee WHERE 1=1");

        Map<String, Object> params = new HashMap<>();

        if (searchRequestDTO.getName() != null && !searchRequestDTO.getName().isEmpty()) {
            queryBuilder.append(" AND name_epl LIKE :name");
            params.put("name", "%" + searchRequestDTO.getName() + "%");
        }
        if (searchRequestDTO.getAddress() != null && !searchRequestDTO.getAddress().isEmpty()) {
            queryBuilder.append(" AND address_epl LIKE :address");
            params.put("address", "%" + searchRequestDTO.getAddress() + "%");
        }
        if (searchRequestDTO.getAge() > 0) {
            queryBuilder.append(" AND age_epl = :age");
            params.put("age", searchRequestDTO.getAge());
        }
        if (searchRequestDTO.getPhone() != null && !searchRequestDTO.getPhone().isEmpty()) {
            queryBuilder.append(" AND phone_epl = :phone");
            params.put("phone", searchRequestDTO.getPhone());
        }
        if (searchRequestDTO.getEmail() != null &&!searchRequestDTO.getEmail().isEmpty()) {
            queryBuilder.append(" AND email_epl = :email");  
            params.put("email", searchRequestDTO.getEmail());
        }
        if (searchRequestDTO.getDepartment() != null &&!searchRequestDTO.getDepartment().isEmpty()) {
            queryBuilder.append(" AND department_epl = :department");
            params.put("department", searchRequestDTO.getDepartment());
        }
        // Kiểm tra và thêm điều kiện tìm kiếm theo khoảng thời gian
        if (searchRequestDTO.getStartDate() != null && searchRequestDTO.getEndDate() == null) {
            queryBuilder.append(" AND start_date_epl >= :startDate");
            params.put("startDate", searchRequestDTO.getStartDate());
        }
        if (searchRequestDTO.getStartDate() == null && searchRequestDTO.getEndDate() != null) {
            queryBuilder.append(" AND start_date_epl <= :endDate");
            params.put("endDate", searchRequestDTO.getEndDate());
        }
        if (searchRequestDTO.getStartDate() != null && searchRequestDTO.getEndDate() != null) {
            queryBuilder.append(" AND (start_date_epl BETWEEN :startDate AND :endDate)");
            params.put("startDate", searchRequestDTO.getStartDate());
            params.put("endDate", searchRequestDTO.getEndDate());
        }

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Employee.class);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setFirstResult(offset);
        query.setMaxResults(searchRequestDTO.getPageSize());

        @SuppressWarnings("unchecked")
		List<Employee> employees = query.getResultList();

        return employees;
    }

    
	/* 3. call procedure*/ 

    
    
	/* 3. new fix */
    @Autowired
    private CustomRepository customRepository;

    public CustomRepository getCustomRepository() {
		return customRepository;
	}

	public void setCustomRepository(CustomRepository customRepository) {
		this.customRepository = customRepository;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	   public SearchResultDTO<?> searchEmployeesPROC(SearchRequestDTO searchRequestDTO) {
        String criteriaString = searchRequestDTO.toCriteriaString();

        List<?> employees = customRepository.searchEmployeesProc(criteriaString);


        @SuppressWarnings("rawtypes")
		SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setData(employees);
        searchResultDTO.setPageNum(searchRequestDTO.getPageNum());
        searchResultDTO.setPageSize(searchRequestDTO.getPageSize());
        searchResultDTO.setTotalPage(employees.size() / searchRequestDTO.getPageSize() + 1);


        return searchResultDTO;
    }
}

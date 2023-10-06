package com.example.ApiSearchDynamic.Reponsitory;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ApiSearchDynamic.Model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class CustomRepositoryImpl implements CustomRepository {
	 @PersistenceContext
	    private EntityManager entityManager;
	    @Override
		public List<Employee> searchEmployeesProc(String searchRequestDTO) {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_employee_data_dynamic_query",Employee.class);
			query.registerStoredProcedureParameter(1, Object.class, ParameterMode.REF_CURSOR)
				 .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				 .setParameter(2, searchRequestDTO);
			query.execute();
			return query.getResultList();
		}
}

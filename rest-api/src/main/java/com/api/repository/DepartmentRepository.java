package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query(value = "select * from department where company_id = ?1", nativeQuery = true)
	List<Department> findAllByCompanyId(Integer companyId);

	@Query(value = "select * from department where company_id = ?1 and department_id = ?2", nativeQuery = true)
	Department findOneByCompanyId(Integer companyId, Integer departmentId);

}

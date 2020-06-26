package com.oracledb.springboot.web.app.customers.dao.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;

public interface ICustomerDAOV1  extends JpaRepository<Customer, Integer> {
	
	@Query("FROM Customer WHERE customer_id = ?1")
	Customer getCustomerV1(int id);

	@Query("FROM Customer WHERE first_name = :firstName")
	Customer getCustomerbyFirstNameV1(@Param("firstName") String firstName);

}

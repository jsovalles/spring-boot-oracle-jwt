package com.oracledb.springboot.web.app.customers.dao.v0;

import java.util.List;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;

public interface ICustomerDAO {
	
	List<Customer> listCustomers();

	Customer getCustomer(int id);

	void createCustomer(Customer customer);

	void updateCustomer(int id, Customer customer);

	void deleteCustomer(int id);

}

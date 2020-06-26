package com.oracledb.springboot.web.app.customers.service;

import java.util.List;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;

public interface ICustomerService {
	
	List<Customer> listCustomers();

	Customer getCustomer(int id);

	void createCustomer(Customer customer);

	void updateCustomer(int id, Customer customer);

	void deleteCustomer(int id);

	List<Customer> listCustomersV1();

	Customer getCustomerV1(int id);

	Customer getCustomerbyFirstNameV1(String firstName);

	void createCustomerV1(Customer customer);

	void updateCustomerV1(int id, Customer customer);

	void deleteCustomerV1(int id);

}

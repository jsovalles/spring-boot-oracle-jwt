package com.oracledb.springboot.web.app.customers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;
import com.oracledb.springboot.web.app.customers.dao.v0.ICustomerDAO;
import com.oracledb.springboot.web.app.customers.dao.v1.ICustomerDAOV1;
import com.oracledb.springboot.web.app.customers.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerDAO customerDAO;
	
	@Autowired
	ICustomerDAOV1 customerDAOV1;
	
	@Override
	@Transactional(readOnly = true)
	public List<Customer> listCustomers() {
		return customerDAO.listCustomers();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void createCustomer(Customer customer) {
		customerDAO.createCustomer(customer);
		
	}

	@Override
	@Transactional
	public void updateCustomer(int id, Customer customer) {

		customerDAO.updateCustomer(id,customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		
		customerDAO.deleteCustomer(id);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Customer> listCustomersV1() {
		return customerDAOV1.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustomerV1(int id) {
		return customerDAOV1.getCustomerV1(id);
	}

	@Override
	@Transactional
	public Customer getCustomerbyFirstNameV1(String firstName) {
		return customerDAOV1.getCustomerbyFirstNameV1(firstName);
	}

	@Override
	@Transactional
	public void createCustomerV1(Customer customer) {
		
		customerDAOV1.save(customer);
		
	}

	@Override
	@Transactional
	public void updateCustomerV1(int id, Customer customer) {
			if(customerDAOV1.getCustomerV1(id)!=null) {
			customer.setId(id);
			customerDAOV1.save(customer);
			}else {
				throw new Error("El usuario no existe en la base de datos");
			}
		
	}

	@Override
	@Transactional
	public void deleteCustomerV1(int id) {
		
		if(customerDAOV1.getCustomerV1(id)!=null) {
		customerDAOV1.deleteById(id);
		}else {
			throw new Error("El usuario no existe en la base de datos");
		}
		
	}


}

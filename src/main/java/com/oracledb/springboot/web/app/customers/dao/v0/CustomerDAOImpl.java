package com.oracledb.springboot.web.app.customers.dao.v0;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {


	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAOImpl.class);
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> listCustomers() {
		return em.createQuery("from Customer").getResultList();
	}

	@Override
	public Customer getCustomer(int id) {
		return  em.find(Customer.class, id);

	}

	@Override
	public void createCustomer(Customer customer) {
		
		em.persist(customer);
	}

	@Override
	public void updateCustomer(int id, Customer customer) {
		em.merge(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		
		em.remove(em.find(Customer.class, id));
	}

	
	

}

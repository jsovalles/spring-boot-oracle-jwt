package com.oracledb.springboot.web.app.customers.facade.v1;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;
import com.oracledb.springboot.web.app.customers.service.ICustomerService;

@RestController
@RequestMapping("/customers/v1/")
public class CustomerControllerV1 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerControllerV1.class);
	
	@Autowired
	ICustomerService service;
	
	@GetMapping
	public List<Customer> listCustomers() {
		List<Customer> customer = service.listCustomersV1();
		return customer;
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable(value="id") int id) {
		Customer customer = service.getCustomerV1(id);
		return customer;
	}
	
	@GetMapping("/customer")
	public Customer getCustomerbyFirstName(@RequestParam("firstName") String firstName) {
		
		Customer customer = service.getCustomerbyFirstNameV1(firstName);

		return customer;
	}
	
	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer customer, HttpServletResponse response) {
		service.createCustomerV1(customer);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return customer;
	}
	
	@PatchMapping("/customer/{id}")
	public void updateCustomer(@PathVariable(value = "id") int id, @RequestBody Customer customer, HttpServletResponse response) {
		service.updateCustomerV1(id,customer);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	@DeleteMapping(path="/customer/{id}")
	public void deleteCustomer(@PathVariable(value = "id") int id, HttpServletResponse response) {
		service.deleteCustomerV1(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}

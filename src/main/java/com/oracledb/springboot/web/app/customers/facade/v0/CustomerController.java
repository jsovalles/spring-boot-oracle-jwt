package com.oracledb.springboot.web.app.customers.facade.v0;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;
import com.oracledb.springboot.web.app.customers.service.ICustomerService;

@Controller
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	ICustomerService service;
	
	@GetMapping("/customers")
	@ResponseBody
	public List<Customer> listCustomers() {
		List<Customer> customer = service.listCustomers();
		return customer;
	}
	
	@GetMapping("/customer/{id}")
	@ResponseBody
	public Customer getCustomer(@PathVariable(value="id") int id) {
		Customer customer = service.getCustomer(id);
		return customer;
	}
	
	@PostMapping(path="/customer",consumes = "application/json")
	@ResponseBody
	public Customer createCustomer(@RequestBody Customer customer, HttpServletResponse response) {
		service.createCustomer(customer);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return customer;
	}
	
	@PatchMapping(path="/customer/{id}",consumes = "application/json")
	@ResponseBody
	public void updateCustomer(@PathVariable(value = "id") int id, @RequestBody Customer customer, HttpServletResponse response) {
		service.updateCustomer(id,customer);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	@DeleteMapping(path="/customer/{id}")
	@ResponseBody
	public void deleteCustomer(@PathVariable(value = "id") int id, HttpServletResponse response) {
		service.deleteCustomer(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}

package com.oracledb.springboot.web.app.customers.facade.v1;

import com.oracledb.springboot.web.app.customers.dao.entity.Customer;
import com.oracledb.springboot.web.app.customers.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customers/v1")
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
	public ResponseEntity<?> getCustomer(@PathVariable(value = "id") int id) {

		Customer customer;
		Map<String, Object> response = new HashMap<>();

		try {
			customer = service.getCustomerV1(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (customer == null) {
			response.put("mensaje", "El cliente ID: ".concat(Integer.toString(id).concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/customer")
	// @Secured("ROLE_ADMIN")
	public Customer getCustomerbyFirstName(@RequestParam("firstName") String firstName) {

		Customer customer = service.getCustomerbyFirstNameV1(firstName);

		return customer;
	}

	@PostMapping("/customer")
	public ResponseEntity createCustomer(@RequestBody Customer customer, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			service.createCustomerV1(customer);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", customer);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PatchMapping("/customer/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") int id, @RequestBody Customer customer, BindingResult result) {
		
		Customer customerVerification = service.getCustomerV1(id);

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (customerVerification == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(Integer.toString(id).concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			service.updateCustomerV1(id, customer);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", customer);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") int id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			service.deleteCustomerV1(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}

}

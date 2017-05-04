package tr.com.ppm.desktop.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.service.CustomerService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@RestController
public class CustomerController {

	private final CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			path = "/rest/customers")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomers(@RequestParam(value = "name", defaultValue = "") String name) {
		return StringUtils.isBlank(name) ? service.list() : service.findAllByName(name);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/rest/customers/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getCustomer(@PathVariable(value = "id") long id) {
		return service.findById(id);
	}

/*	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/customers")
	@ResponseStatus(HttpStatus.OK)
	public String createCustomer(@RequestParam(value = "name") String name) {
		service.save(new Customer(name));
		return "OK";
	}*/

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/customers")
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	String addCustomer(@RequestBody Customer customer) {
		service.save(customer);
		return "{\"result\": \"Customer saved!\"}";
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			path = "/rest/customers")
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	String updateCustomer(@RequestBody Customer customer) {
		service.update(customer);
		return "{\"result\": \"Customer updated!\"}";
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			path = "/rest/customers")
	@ResponseStatus(HttpStatus.OK)
	public
	@ResponseBody
	String deleteCustomer(@RequestParam(value = "id") long id) {
		Customer customer = service.findById(id);
		service.remove(customer);
		return "{\"result\": \"Customer deleted!\"}";
	}

	@RequestMapping(
			method = RequestMethod.OPTIONS,
			path = "/rest/customers")
	@ResponseStatus(value = HttpStatus.OK)
	public
	@ResponseBody
	List<RequestMethod> options() {
		return Arrays.asList(RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS);
	}

	/*
	* @RequestParam
	* @PathVariable
	* how to send 503 response
	* */

}

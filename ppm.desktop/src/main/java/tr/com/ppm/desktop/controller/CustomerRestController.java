package tr.com.ppm.desktop.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.service.CustomerService;

import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService service;

	@RequestMapping(
			method = RequestMethod.GET,
			path = "/rest/customers")
	public List<Customer> getCustomers(@RequestParam(value = "name", defaultValue = "") String name) {
		return StringUtils.isBlank(name) ? service.list() : service.findAllByName(name);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/rest/customers/{id}")
	public Customer getCustomer(@PathVariable(value = "id") long id) {
		return service.findById(id);
	}

	@RequestMapping(
			method = RequestMethod.POST,
			path = "/rest/customers")
	public String createCustomer(@RequestParam(value = "name") String name) {
		service.save(new Customer(name));
		return "OK";
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			path = "/rest/customers")
	public String updateCustomer(@RequestParam(value = "id") long id, @RequestParam(value = "name") String name) {
		Customer customer = service.findById(id);
		customer.setName(name);
		service.update(customer);
		return "OK";
	}

	@RequestMapping(
			method = RequestMethod.DELETE,
			path = "/rest/customers")
	public String deleteCustomer(@RequestParam(value = "id") long id) {
		Customer customer = service.findById(id);
		service.remove(customer);
		return "OK";
	}

	/*
	* @RequestParam
	* @PathVariable
	*
	* */

}

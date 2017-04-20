package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.repository.CustomerRepository;

import java.util.List;

/**
 * @author ykarabalkan.
 */

@Service
public class CustomerService {

	private final CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer findById(Long id) {
		return repository.findOne(id);
	}

	public List<Customer> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

	public void saveCustomer(Customer customer) {
		repository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		saveCustomer(customer);
	}

	public void deleteCustomer(Customer customer) {
		repository.delete(customer);
	}

	public void deleteCustomerById(Long id) {
		repository.delete(id);
	}

	public void deleteAllCustomers() {
		repository.deleteAll();
	}

	public List<Customer> findAll() {
		return repository.findAll();
	}

}

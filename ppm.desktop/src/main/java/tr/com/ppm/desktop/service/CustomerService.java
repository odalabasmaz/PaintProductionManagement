package tr.com.ppm.desktop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.repositories.CustomerRepository;

import java.util.List;

/**
 * @author ykarabalkan.
 */

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(Long id) {
		return customerRepository.findOne(id);
	}

	public List<Customer> findByName(String name) {
		return customerRepository.findByName(name);
	}

	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) {
		saveCustomer(customer);
	}

	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	public void deleteCustomerById(Long id) {
		customerRepository.delete(id);
	}

	public void deleteAllCustomers() {
		customerRepository.deleteAll();
	}

	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

}

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
public class CustomerService extends BaseNewService<Customer, Long> {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		super(repository);
	}

	public List<Customer> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

}

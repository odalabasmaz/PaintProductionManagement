package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.customer.Customer;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class CustomerService extends BaseService<Customer> {
	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}
}

package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.customer.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class CustomerService extends BaseService<Customer> {
	private static final String QUERY_BY_NAME = "from Customer where lower(name) like :name";
	private static final String QUERY_BY_ID = "from Customer where id = :id";

	@Override
	protected Class<Customer> getEntityClass() {
		return Customer.class;
	}

	public Customer findById(long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return executeQuery(QUERY_BY_ID, params).get(0);
	}

	public Customer findByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		List<Customer> customers = executeQuery(QUERY_BY_NAME, params);
		return customers.size() != 1 ? null : customers.get(0);
	}

	public List<Customer> findAllByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", "%" + name.trim().toLowerCase() + "%");
		return executeQuery(QUERY_BY_NAME, params);
	}

}

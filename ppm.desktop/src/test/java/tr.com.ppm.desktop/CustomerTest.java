package tr.com.ppm.desktop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.service.CustomerService;

/**
 * @author Orhun Dalabasmaz
 */
@RunWith(SpringRunner.class)
public class CustomerTest {

	@Autowired
	private CustomerService service;

	@Test
	public void addCustomer() {
		Customer customer = new Customer("ayça");
		service.save(customer);
		System.out.printf("saved");
		System.out.println(service.list().size());
	}
}

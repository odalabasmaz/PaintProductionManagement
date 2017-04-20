package tr.com.ppm.desktop.repository;

import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.customer.Customer;

import java.util.List;

/**
 * @author ykarabalkan.
 */
@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {

	List<Customer> findByNameContainingIgnoreCase(String name);

}

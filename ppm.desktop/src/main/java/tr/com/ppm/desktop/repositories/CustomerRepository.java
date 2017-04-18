package tr.com.ppm.desktop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.ppm.desktop.model.customer.Customer;

import java.util.List;

/**
 * @author ykarabalkan.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);

}

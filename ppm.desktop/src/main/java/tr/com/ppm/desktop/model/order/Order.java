package tr.com.ppm.desktop.model.order;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.customer.Customer;

import java.util.Date;
import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
public class Order extends AuditableEntity {
	private Customer customer;
	private Date dueDate;
	private List<ProductOrder> productOrders;
}

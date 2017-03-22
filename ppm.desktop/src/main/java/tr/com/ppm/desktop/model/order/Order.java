package tr.com.ppm.desktop.model.order;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "ORDER")
public class Order extends AuditableEntity {

	@Column(name = "ORDER_ID")
	private String orderId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private List<OrderItem> orderItem = new ArrayList<>();

	/*for hibernate*/
	Order() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void addOrderItem(OrderItem orderItem) {
		this.orderItem.add(orderItem);
	}

	public void removeOrderItem(OrderItem orderItem) {
		this.orderItem.remove(orderItem);
	}
}

package tr.com.ppm.desktop.model.order;

import tr.com.ppm.desktop.model.common.AuditableEntity;
import tr.com.ppm.desktop.model.common.Container;
import tr.com.ppm.desktop.model.material.Product;

import javax.persistence.*;

/**
 * @author Orhun Dalabasmaz
 */
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem extends AuditableEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTAINER_ID")
	private Container container;

	@Column(name = "QUANTITY")
	private Integer quantity;

	/*for hibernate*/
	OrderItem() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
